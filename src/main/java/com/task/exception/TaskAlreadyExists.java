package com.task.exception;

public class TaskAlreadyExists extends RuntimeException {
	private final String title;

	public TaskAlreadyExists(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
