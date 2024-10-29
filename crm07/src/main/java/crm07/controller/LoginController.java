package crm07.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.services.LoginService;

@WebServlet(name="loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	private LoginService loginService = new LoginService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		loginService.checkLoginCookie(req);
		
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pEmail = req.getParameter("email");
		String pPassword = req.getParameter("password");
		String isRemember = req.getParameter("remember");
		
		
		boolean isSuccess = loginService.checkLogin(pEmail, pPassword, resp, isRemember);
		
		
		System.out.println("login is " + isSuccess);
		
		if (isSuccess) {
			resp.sendRedirect(req.getContextPath() + "/");
			
		}
		
		
	}
	
}
