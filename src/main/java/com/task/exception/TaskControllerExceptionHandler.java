package com.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.task.exception.TaskException.Response;

@ControllerAdvice
public class TaskControllerExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		return new ResponseEntity<>(Response.fail("message taken from the exception"), HttpStatus.OK);
	}

	@ExceptionHandler(TaskAlreadyExists.class)
	public ResponseEntity<Object> handleTaskAlreadyExistsException(TaskAlreadyExists ex, WebRequest request) {
		return new ResponseEntity<>(Response.fail("Task with title: " + ex.getTitle() + " already exists"),
				HttpStatus.OK);
	}

	@ExceptionHandler(TaskNotFound.class)
	public ResponseEntity<Object> handleTaskNotFoundException(TaskNotFound ex, WebRequest request) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
