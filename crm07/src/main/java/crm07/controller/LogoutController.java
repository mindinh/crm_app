package crm07.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.services.LogoutService;


@WebServlet(name = "logoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
	private LogoutService logoutService = new LogoutService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/logout":
				logout(req, resp);
			break;
		
		}
		
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logoutService.logout(req, resp);
		
		resp.sendRedirect(req.getContextPath() + "/");
	}
	
	
}
