package com.task.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.task.dto.TaskDto;
import com.task.dto.TaskStatus;

@Entity
@Table(name = "Task", uniqueConstraints = @UniqueConstraint(columnNames = { "title" }))
public class Task {

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String description;

	private String status;

	public Task() {
		super();
	}

	public Task(String title) {
		super();
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TaskDto toDto() {
		TaskDto dto = new TaskDto();
		dto.setId(this.getId());
		dto.setTitle(this.getTitle());
		dto.setDescription(this.getDescription());
		dto.setStatus(this.getStatus());
		return dto;
	}

	public void changeStatusTo(TaskStatus taskStatus) {
		this.setStatus(taskStatus.getCode());
	}

	public void changeTitle(String title) {
		this.setTitle(title);
	}

}
