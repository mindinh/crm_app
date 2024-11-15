package crm07.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.TaskEntity;
import crm07.services.ProfileService;

@WebServlet(name="profileController", urlPatterns = {"/profile", "/profile-edit"})
public class ProfileController extends HttpServlet {
	private ProfileService profileService = new ProfileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/profile":
				loadUser(req, resp);
			break;
			case "/profile-edit":
				loadUserTask(req, resp);
			break;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			
			case "/profile-edit":
				editUserTask(req, resp);
			break;
		}
	}
	
	private void loadUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		profileService.getUserAndTasks(req, resp);
		
	}
	
	private void loadUserTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pTaskId = req.getParameter("id");
		
		profileService.getUserTask(req, resp, pTaskId);
	
		
	}
	
	private void editUserTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String pTaskId = req.getParameter("id");
		String pStatus = req.getParameter("statusname");
		
		
		if (profileService.updateUserTask(req, resp, pTaskId, pStatus)) {
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		
		
		
	}
	
	
}
