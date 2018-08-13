package com.jdivirgilio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		// useSSL=false gets rid of MySQL warnings
		String jdbcURL = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "hbstudent";
		String pwd = "hbstudent";
		
		try {
			System.out.println("Connection to DB: " + jdbcURL);
			Connection myConn = DriverManager.getConnection(jdbcURL,  user, pwd);
			System.out.println("Connection successful");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
