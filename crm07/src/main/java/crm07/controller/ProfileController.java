package crm07.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.services.ProfileService;

@WebServlet(name="profileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
	private ProfileService profileService = new ProfileService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/profile":
				loadUser(req, resp);
				break;
		}
		
	}
	
	private void loadUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		profileService.getUser(req, resp);
		
	}
	
}
