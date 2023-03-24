package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInfo {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/tochucthi?characterEncoding=utf8";

	public static final String USER = "root";
	public static final String PASS = "long1402";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("!!Lỗi: " + e.getMessage());
			return null;
		}

	}
}
