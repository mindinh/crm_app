package crm07.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.RoleEntity;
import crm07.services.RoleService;

@WebServlet(name="roleController", urlPatterns = {"/roles", "/role-add"})
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/roles":
				loadRoles(req, resp);
			break;
		}
	}
	
	private void loadRoles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<RoleEntity> roleList = roleService.getAllRoles();
		
		req.setAttribute("roleList", roleList);
		req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
	}
}
