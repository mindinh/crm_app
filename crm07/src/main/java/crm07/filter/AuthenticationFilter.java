package crm07.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




// urlPatterns đường dẫn sẽ chạy filter
@WebFilter(filterName = "authenFilter", urlPatterns = {"/users"})
public class AuthenticationFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Filtered!");
		
		String servletPath = req.getServletPath();
		Cookie[] cookies = req.getCookies();
		String authen = null;
		
		for (Cookie c : cookies) {
			if (c.getName().equals("authen")) {
				authen = c.getValue();
			}
					
			
		}
		
		if (authen != null) {
			switch (servletPath) {
				case "/users":
					if (authen.equals("ROLE_ADMIN")) {
						chain.doFilter(req, res);
					}
					else {
						res.sendRedirect(req.getContextPath());
					}
				break;

			}
		}
		else {
			res.sendRedirect(req.getContextPath() + "/login");
		}
	


		
		
		
	}
	
}
