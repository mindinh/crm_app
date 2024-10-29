package crm07.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.RoleEntity;
import crm07.entity.UserEntity;
import crm07.services.UserService;

@WebServlet(name="userController", urlPatterns = {"/users", "/user-add"})
public class UserController extends HttpServlet{
	
	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch(path) {
			case "/users":
				loadUsers(req, resp);
				
			break;
			case "/user-add":
				addUser(req, resp);
				
			break;
		}
		
		
	}
	
	private void loadUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		if (id != null) {
			userService.deleteUserById(Integer.parseInt(id));
			
		}
		
		ArrayList<UserEntity> userList = userService.getAllUser();
		
		req.setAttribute("userList", userList);
		req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
	}
	
	private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<RoleEntity> roleList = userService.getAllRoles();
		
		req.setAttribute("roleList", roleList);
		req.getRequestDispatcher("/user-add.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/user-add":
				addUserPost(req, resp);
			break;
		}
	}
	
	private void addUserPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pName = req.getParameter("fullname");
		String pEmail = req.getParameter("email");
		String pPassword = req.getParameter("password");
		String pRoleId = req.getParameter("role");
		
		
		userService.insertUser(pName, pEmail, pPassword, Integer.parseInt(pRoleId));
		
		resp.sendRedirect(req.getContextPath() + "/users");
	}
		

}
