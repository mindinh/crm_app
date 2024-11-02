package crm07.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.UserEntity;
import crm07.repository.UserRepository;

public class ProfileService {
	private UserRepository userRepository = new UserRepository();
	
	public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Cookie[] cookies = req.getCookies();
		
		String value = "";
		try {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userid")) {
					value = cookie.getValue();
				}
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(value);
		
		ArrayList<UserEntity> userList = userRepository.findByEmail(value);
		
		req.setAttribute("username", userList.get(0).getFullname());
		req.setAttribute("email", userList.get(0).getEmail());
		
		req.getRequestDispatcher("profile.jsp").forward(req, resp);
		
	}
}
