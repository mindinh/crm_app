package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/crm_app", "root", "sa123");
		}
		catch (Exception e) {
			System.out.println("Error connecting to db");
		}
		
		
		return conn;
		
	}
}
