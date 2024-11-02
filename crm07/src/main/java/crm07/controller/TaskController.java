package crm07.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.TaskEntity;
import crm07.services.TaskService;

@WebServlet(name="taskController", urlPatterns = {"/tasks"})
public class TaskController extends HttpServlet {
	
	private TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/tasks":
				loadTasks(req, resp);
				
			break;
		}
	}
	
	private void loadTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<TaskEntity> taskList = taskService.getAllTasks();
		req.setAttribute("taskList", taskList);
		
		
		req.getRequestDispatcher("task.jsp").forward(req, resp);
		
	}
}
