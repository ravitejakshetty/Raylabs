package com.raylabs.data;

import java.sql.Timestamp;

public class Task {

	private int taskId;
	private String taskTitle;
	private String assignedUserId;
	private String taskType;
	private Timestamp dueDate;
	private Timestamp createdDate;

	public Task(int taskId, String taskTitle, String assignedUserId,
			String taskType, Timestamp dueDate, Timestamp createdDate) {

		this.taskId = taskId;
		this.assignedUserId = assignedUserId;
		this.taskTitle = taskTitle;
		this.taskType = taskType;
		this.dueDate = dueDate;
		this.createdDate = createdDate;

	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(String assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

}
