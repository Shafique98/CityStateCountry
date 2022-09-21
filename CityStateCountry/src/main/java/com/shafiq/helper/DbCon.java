package com.shafiq.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbCon {
	private static Connection con = null;
	
	public static Connection getCon() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver"); //registering and loading the driver
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hibernate", "hibernate"); //establishing connection
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
