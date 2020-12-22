package com.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.TaskDto;
import com.task.dto.TaskStatus;
import com.task.entity.Task;
import com.task.exception.TaskAlreadyExists;
import com.task.exception.TaskNotFound;
import com.task.repository.TaskRepository;

@RestController
public class TaskController {
	private final TaskRepository repository;

	@Autowired
	public TaskController(TaskRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<TaskDto> findAll() {
		List<Task> tasks = new ArrayList<>();
		repository.findAll().forEach(task -> tasks.add(task));
		return tasks.stream().map(Task::toDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public TaskDto findById(@PathVariable Long id) {
		Optional<Task> task = repository.findById(id);
		if (task.isPresent()) {
			return task.get().toDto();
		}
		throw new TaskNotFound(id);
	}

	@PostMapping
	public Long create(@RequestBody TaskDto dto) {
		Task task = new Task(dto.getTitle());
		task.setDescription(dto.getDescription());
		task.changeStatusTo(statusOf(dto).get());
		try {
			return repository.save(task).getId();
		} catch (DataIntegrityViolationException exception) {
			throw new TaskAlreadyExists(dto.getTitle());
		}
	}

	@PutMapping("/{id}")
	public void modify(@RequestBody TaskDto dto, @PathVariable Long id) {
		Optional<Task> found = repository.findById(id);
		if (found.isPresent()) {
			Task task = found.get();
			Optional<TaskStatus> taskStatus = statusOf(dto);
			taskStatus.ifPresent(task::changeStatusTo);
			if (dto.getDescription() != null) {
				task.setDescription(dto.getDescription());
			}
			if (dto.getTitle() != null) {
				task.changeTitle(dto.getTitle());
			}
			repository.save(task);
		} else {
			throw new TaskNotFound(id);
		}
	}

	private Optional<TaskStatus> statusOf(TaskDto dto) {
		return Optional.of(TaskStatus.valueOf(dto.getStatus()));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Task> task = repository.findById(id);
		if (task.isPresent()) {
			repository.delete(task.get());
		} else {
			throw new TaskNotFound(id);
		}
	}

}
