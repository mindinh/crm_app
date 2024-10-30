package demobc07;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="nhanvienServlet", urlPatterns = {"/nhanvien"})
public class NhanVienServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// open connection to JDBC
		Connection conn = JDBCConnection.getConnection();
		
		String query = "SELECT * FROM employees";
		
		try {
			// prepare query for JDBC
			PreparedStatement statement = conn.prepareStatement(query);
			
			// execute query
			/* 2 types of exec
			 *  
			 *  + executeQuery : used when query is a SELECT
			 *  
			 *   
			 *  + executeUpdate : not SELECT (INSERT, UPDATE, DELETE...)
			 *   
			*/
			
			ResultSet res = statement.executeQuery();
			
			// create empty list to store data from query
			ArrayList<EmployeeEntity> list = new ArrayList<EmployeeEntity>();
			
			while (res.next()) {
				EmployeeEntity e = new EmployeeEntity();
				e.setId(res.getInt("employee_id"));
				e.setName(res.getString("employee_name"));
				e.setSalary(res.getDouble("salary"));
				e.setDepartmentId(res.getInt("department_id"));
				
				list.add(e);
			}
			
			System.out.println("test " + list.size());
			
			// return data to jsp file
			req.setAttribute("employeesList", list);
			
			req.getRequestDispatcher("nhanvien.jsp").forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("Error query " + e.getMessage());

		}
		
		
		
		
	}
}
