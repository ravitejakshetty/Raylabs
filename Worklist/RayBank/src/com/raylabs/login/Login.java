package com.raylabs.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.raylabs.data.ConnectionBean;
import com.raylabs.user.User;

@ManagedBean(name = "user", eager = true)
public class Login {

	private String userName;
	private String password;
	private boolean isUserNameValid;
	private boolean isPasswordValid;
	private boolean isPasswordValidOne;
	private boolean validationComplete = false;
	private String outcome="fail";
	
	@ManagedProperty("#{activeUser}")
    private User user;
	
	public boolean getIsPasswordValidOne() {
		return isPasswordValidOne;
	}

	public void setIsPasswordValidOne(boolean isPasswordValidOne) {
		this.isPasswordValidOne = isPasswordValidOne;
	}

	public boolean getIsUserNameValid() {
		return isUserNameValid;
	}

	public void setIsUserNameValid(boolean isUserNameValid) {
		this.isUserNameValid = isUserNameValid;
	}

	public boolean getIsPasswordValid() {
		return isPasswordValid;
	}

	public void setIsPasswordValid(boolean isPasswordValid) {
		this.isPasswordValid = isPasswordValid;
	}

	public boolean getValidationComplete() {
		return validationComplete;
	}

	public void setValidationComplete(boolean validationComplete) {
		this.validationComplete = validationComplete;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		if (this.userName == null || this.userName.equals("")) {
			isUserNameValid = false;
		} else {
			isUserNameValid = true;
		}
		if (this.password == null || this.password.equals("")) {
			isPasswordValid = false;
			isPasswordValidOne = true;
		} else {
			Connection conn = ConnectionBean.getConnection();
			try {
				PreparedStatement psStmt = conn
						.prepareStatement("select password from users where user_name=?");
				psStmt.setString(1, this.userName.toUpperCase());
				ResultSet rs = psStmt.executeQuery();
				// STEP 5: Extract data from result set
				int rowCount = 0;
				String pwd = null;
				while (rs.next()) {
					rowCount++;
					// Retrieve by column name
					pwd = rs.getString("password");
				}
				rs.close();
    			if (rowCount > 0) {
					isPasswordValid = true;
					isPasswordValidOne = true;
					if (this.password.equals(pwd)) {
						isPasswordValidOne = true;
						isPasswordValid = true;
						outcome="pass";
						user.setUserId(this.userName);
						user.getDetails();
					} else {
						isPasswordValidOne = false;
						isPasswordValid = true;
					}
				} 
    			else
    			{
					isPasswordValid = true;
					isPasswordValidOne = false;
				}

			} catch (Exception e) {
				e.printStackTrace();
				isPasswordValid = true;
				isPasswordValidOne = false;
			}
		}
		validationComplete = true;
		return outcome;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
