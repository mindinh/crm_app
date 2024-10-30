package demobc07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.WriteBuffer;

@WebServlet(name = "helloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {
	
	// default sẽ kích hoạt pt get khi gọi đường dẫn mà không chỉ định phương thức
	
	/* 
	 * Phương thức: cách truyền và nhận tham số
	 * GET tham số sẽ truyền trực tiếp trên đường dẫn
	 *  - Cú pháp : ?tên_tham_số=giá_trị&...
	 *  
	 *  ví dụ : https://vnexpres?danhmuc=thoisu&...
	 *  
	 *  - Nhận giá trị tham số từ client để xử lý : request.getParameter("tên_tham_số")
	 *  
	 *  ví dụ : dòng 38
	 *  
	 * POST: tham số sẽ được truyền ngầm
	 * 
	 * 
	 * */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// lấy tham số có tên là hoten từ client truyền lên
//		String pHoten = req.getParameter("hoten");
//		
//		PrintWriter out = resp.getWriter();
//		out.println("Hi " + pHoten);
//		out.close();
		
		// tra ra giao dien file html cho client
		req.getRequestDispatcher("hello.html").forward(req, resp);
		
	}
	
	// Kích hoạt khi người dùng gọi đường dẫn với phương thức POST 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pUsername = req.getParameter("username");
		String pPassword = req.getParameter("password");
		
		PrintWriter writer = resp.getWriter();
		writer.println("Welcome hello post! " + pUsername + " " + pPassword);
		writer.close();
		
	}
}
