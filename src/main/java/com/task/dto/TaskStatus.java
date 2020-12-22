package com.task.dto;

public enum TaskStatus {

	COMPLETED("DONE"),

	WORK_IN_PROGRESS("WIP"),

	PARTIAL_COMPLETED("PARTIAL_DONE");

	private String code;

	private TaskStatus(String code) {
		this.code = code;
	}

	public static TaskStatus getEnum(String val) {
		for (TaskStatus sts : TaskStatus.values()) {
			if (sts.getCode().equals(val)) {
				return sts;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
