package com.raylabs.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.raylabs.data.ConnectionBean;

@ManagedBean(name = "activeUser", eager = true)
@SessionScoped
public class User {

	private String userId;
	private String firstName;
	private String lastName;
	private boolean authenticated;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void getDetails() throws Exception {
		authenticated = true;
		Connection conn = ConnectionBean.getConnection();
		String sql = "select first_name, last_name from users where user_name=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, this.userId);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			this.firstName = rs.getString("first_name");
			this.lastName = rs.getString("last_name");
		}
		rs.close();
	}

	public boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "/index.xhtml?faces-redirect=true";

	}

}
