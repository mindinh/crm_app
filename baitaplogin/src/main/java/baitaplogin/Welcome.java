package baitaplogin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="welcome", urlPatterns = {"/welcome"})
public class Welcome extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pUsername = req.getParameter("username");
		String pPassword = req.getParameter("password");
		
		req.setAttribute("username", pUsername);
		req.setAttribute("password", pPassword);
		
		req.getRequestDispatcher("welcome.jsp").forward(req, resp);
		
	}
	
}
