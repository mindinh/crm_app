package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.NewsEntity;
import service.NewsService;

@WebServlet(name="newsController", urlPatterns = {"/news"})
public class NewsController extends HttpServlet {
	
	private NewsService newsService = new NewsService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<NewsEntity> newsList = newsService.getAllNews();
		
		req.setAttribute("newsList", newsList);
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
}
