package com.task.exception;

public class TaskNotFound extends RuntimeException {
	public TaskNotFound(Long id) {
		super("Task with id: " + id + " not found.");
	}
}
