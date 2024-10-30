package buoi25;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="loginServlet", urlPatterns = {"/login"})
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pUsername = req.getParameter("username");
		String pPassword = req.getParameter("password");
		
		if (pUsername.equals("admin") && pPassword.equals("123456")) {
			resp.sendRedirect(req.getContextPath() + "/welcome");
		}
		else {
			req.setAttribute("error", true);
			
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
}
