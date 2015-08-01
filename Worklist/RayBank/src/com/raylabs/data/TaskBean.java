package com.raylabs.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.raylabs.user.User;

@ManagedBean(name = "userTasks", eager = false)
public class TaskBean {

	@ManagedProperty(value = "#{activeUser}")
	private User user;
	private List<Task> tasks;

	public List<Task> getTasks() throws Exception {
		tasks = new ArrayList<Task>();
		Connection conn = ConnectionBean.getConnection();
		String sql = "select TASK_ID,ASSIGNED_USER_NAME,TASK_TITLE,TASK_TYPE,DUE_DATE,CREATED_DATE from tasks where ASSIGNED_USER_NAME=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, this.user.getUserId());
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			int taskId = rs.getInt("TASK_ID");
			String taskTitle = rs.getString("TASK_TITLE");
			String userId = rs.getString("ASSIGNED_USER_NAME");
			String taskType = rs.getString("TASK_TYPE");
			Timestamp dueDate = rs.getTimestamp("DUE_DATE");
			Timestamp createdDate = rs.getTimestamp("CREATED_DATE");
			tasks.add(new Task(taskId, taskTitle, userId, taskType, dueDate,
					createdDate));
		}
		rs.close();
		return tasks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
