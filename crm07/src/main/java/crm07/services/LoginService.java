package crm07.services;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.UserEntity;
import crm07.repository.UserRepository;
import crm07.utils.MD5;

public class LoginService {
	private UserRepository userRepository = new UserRepository();
	
	public boolean checkLogin(String email, String password, HttpServletResponse resp, String remember) {
		String passwordEncoded = MD5.getMd5(password);
		ArrayList<UserEntity> listUserEntity = userRepository.findByEmailAndPassword(email, passwordEncoded);
		boolean isSuccess = listUserEntity.size() > 0;
		
		if (isSuccess) {
			if (remember != null) {
				Cookie emailCookie = new Cookie("email", email);
				Cookie passCookie = new Cookie("password", password);
				resp.addCookie(emailCookie);
				resp.addCookie(passCookie);
			}
			
			Cookie authenCookie = new Cookie("authen", listUserEntity.get(0).getRoleName());
			resp.addCookie(authenCookie);
		}

		return isSuccess;
	}
	
	
	
	
	
	public void checkLoginCookie(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		
		try {
			String value = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email")) {
					value += cookie.getValue() + "/";
				}
				
				if (cookie.getName().equals("password")) {
					value += cookie.getValue();
				}
			}
			
			req.setAttribute("email", value.split("/")[0]);
			req.setAttribute("password", value.split("/")[1]);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
