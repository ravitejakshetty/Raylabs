package com.raylabs.data;

import java.sql.*;

public class ConnectionBean {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/RAYLABS";
	static final String USER = "ray";
	static final String PASS = "ray";

	public static Connection getConnection() {

		Connection conn = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// STEP 3: Open a connection
			//System.out.println("Connecting to a selected database !!");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//System.out.println("Connected database successfully...");
		} catch (SQLException se) {
			//System.out.println("eRROR SQL");
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			//System.out.println("eRROR SQL 2");
			e.printStackTrace();
		}
		return conn;
	}

}
