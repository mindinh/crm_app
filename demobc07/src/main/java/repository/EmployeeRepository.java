package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import demobc07.EmployeeEntity;
import demobc07.JDBCConnection;

/*
*	Quản lý tất cả câu truy vấn liên quan đến bảng Employees
*/
public class EmployeeRepository {
	
	public ArrayList<EmployeeEntity> findAll() {
		String query = "SELECT * FROM employees";
		Connection conn = JDBCConnection.getConnection();
		ArrayList<EmployeeEntity> empList = new ArrayList<EmployeeEntity>();
		
		try {
			PreparedStatement prepStatment = conn.prepareStatement(query);
			
			ResultSet res = prepStatment.executeQuery();
			
			
			
			while (res.next()) {
				EmployeeEntity e = new EmployeeEntity();
				e.setId(res.getInt("employee_id"));
				e.setName(res.getString("employee_name"));
				e.setSalary(res.getDouble("salary"));
				e.setDepartmentId(res.getInt("department_id"));
				
				empList.add(e);
			}
			
			System.out.println("size " + empList.size());
			
		}
		catch (Exception e) {
			System.out.println("Find all error " + e.getMessage());
		}
		
		return empList;
	}
	
}
