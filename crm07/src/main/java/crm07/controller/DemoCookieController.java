package crm07.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="cookieController", urlPatterns = {"/demo-cookie"})
public class DemoCookieController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Cookie cook = new Cookie("demo", URLEncoder.encode("hello cookie", "UTF-8"));
//		
//		resp.addCookie(cook);
//		Cookie cook2 = new Cookie("demo2", URLEncoder.encode("hello cookie 2", "UTF-8"));
//		
//		resp.addCookie(cook2);
		
		Cookie[] cookies = req.getCookies();
		for (int i=0; i<cookies.length; i++) {
			
			if (cookies[i].getName().equals("demo")) {
				System.out.println("Demo = " + URLDecoder.decode(cookies[i].getValue(), "UTF-8"));
			}
		}
	}
	
}
