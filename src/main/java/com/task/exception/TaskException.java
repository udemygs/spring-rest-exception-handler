package com.task.exception;

public class TaskException {
	static class Response {
		private static final boolean FAILURE = false;
		private final boolean success;
		private final String message;

		Response(boolean success, String message) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public String getMessage() {
			return message;
		}

		static Response fail(String message) {
			return new Response(FAILURE, message);
		}
	}
}
