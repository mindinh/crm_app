package crm07.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutService {
	
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie authenCookie = new Cookie("authen", "");
		authenCookie.setMaxAge(0);
		
		Cookie useridCookie = new Cookie("userid", "");
		authenCookie.setMaxAge(0);

		Cookie emailCookie = new Cookie("email", "");
		authenCookie.setMaxAge(0);

		Cookie passwordCookie = new Cookie("password", "");
		authenCookie.setMaxAge(0);


		resp.addCookie(authenCookie);
		resp.addCookie(useridCookie);
		resp.addCookie(emailCookie);
		resp.addCookie(passwordCookie);
		
		
		
	}

}
