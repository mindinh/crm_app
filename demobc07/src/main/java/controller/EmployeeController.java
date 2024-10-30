package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import demobc07.EmployeeEntity;
import service.EmployeeService;

@WebServlet(name="employeeController", urlPatterns = {"/ds/employees"})
public class EmployeeController extends HttpServlet {
	private EmployeeService empService = new EmployeeService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<EmployeeEntity> empList = empService.getAllEmployee();
		
		req.setAttribute("employeesList", empList);
		
		req.getRequestDispatcher("/nhanvien.jsp").forward(req, resp);
		
	}
	
}
