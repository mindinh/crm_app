package demobc07;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {
	
	
	
	// define function to open connection to database ( for re-using )
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			// declare driver for JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// use driver to open connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo07", "root", "sa123");

		} catch (Exception e) {
			
			System.out.println("Error connecting to db");
		
		} 
		
		
		return conn;
	}
	
	
}
