package demobc07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.sendRedirect(req.getContextPath() + "/login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pUsername = req.getParameter("username");
		String pPassword = req.getParameter("password");
		
		req.setAttribute("username" , pUsername);
		

		if (pUsername.equals("admin") && pPassword.equals("123456")) {
			
			// getCOntextPath giup lay ten project 
			req.getRequestDispatcher("welcome.jsp").forward(req, resp);
			
		}
		
	}
}
