package crm07.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.JobEntity;
import crm07.entity.TaskEntity;
import crm07.entity.UserEntity;
import crm07.services.TaskService;

@WebServlet(name="taskController", urlPatterns = {"/tasks", "/task-add"})
public class TaskController extends HttpServlet {
	
	private TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/tasks":
				loadTasks(req, resp);
				
			break;
			case "/task-add":
				loadTask(req, resp);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/task-add":
				insertTask(req, resp);
			break;
		}

	}
	
	private void loadTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("id");
		if (taskId != null) {
			taskService.deleteTask(Integer.parseInt(taskId));
		}
		
		ArrayList<TaskEntity> taskList = taskService.getAllTasks();
		req.setAttribute("taskList", taskList);
		
		
		req.getRequestDispatcher("task.jsp").forward(req, resp);
		
	}
	
	private void loadTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<JobEntity> jobList = taskService.getAllJobs();
		ArrayList<UserEntity> userList = taskService.getAllUsers();
		
		req.setAttribute("userList", userList);
		req.setAttribute("jobList", jobList);
		
		req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
		
	}
	
	private void insertTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pJobName = req.getParameter("jobname");
		String pTaskName = req.getParameter("taskname");
		String pUsername = req.getParameter("username");
		String pStartDate = req.getParameter("startdate");
		String pEndDate = req.getParameter("enddate");
		
		
		if (taskService.insertATask(pJobName, pTaskName, pUsername, pStartDate, pEndDate)) {
			resp.sendRedirect(req.getContextPath() + "/tasks");
		}
		
	}
}
