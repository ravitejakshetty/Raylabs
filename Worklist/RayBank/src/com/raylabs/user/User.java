package com.raylabs.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.raylabs.data.ConnectionBean;

@ManagedBean(name = "activeUser", eager = true)
@SessionScoped
public class User {

	private String userId;
	private String firstName;
	private String lastName;
	private String password;
	private String country;
	private boolean authenticated;
	private int sessionTimeout = 300;

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
		String sql = "select first_name, last_name , password, country from users where user_name=?";
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.setString(1, this.userId);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			this.firstName = rs.getString("first_name");
			this.lastName = rs.getString("last_name");
			this.password = rs.getString("password");
			this.country = rs.getString("country");
		}
		rs.close();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		session.setMaxInactiveInterval(sessionTimeout);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
