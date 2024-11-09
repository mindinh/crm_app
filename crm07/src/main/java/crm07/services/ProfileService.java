package crm07.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.TaskEntity;
import crm07.entity.UserEntity;
import crm07.repository.TaskRepository;
import crm07.repository.UserRepository;

public class ProfileService {
	private UserRepository userRepository = new UserRepository();
	private TaskRepository taskRepository = new TaskRepository();
	
	public void getUserAndTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
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
		
		
		ArrayList<UserEntity> userList = userRepository.findByEmail(value);
		ArrayList<TaskEntity> taskList = taskRepository.findByEmail(value);
		double numOfTasks = taskList.size();
		double numOfFinishedTasks = 0, numOfInProgressTasks = 0, numOfUnfinishedTasks = 0;
		
		for (TaskEntity t : taskList) {
			if (t.getStatus().equals("Đã hoàn thành")) {
				numOfFinishedTasks += 1;
			}
			if (t.getStatus().equals("Đang thực hiện")) {
				numOfInProgressTasks += 1;
			}
			if (t.getStatus().equals("Chưa thực hiện")) {
				numOfUnfinishedTasks += 1;
			}
		}
		
		
		req.setAttribute("taskList", taskList);
		req.setAttribute("username", userList.get(0).getFullname());
		req.setAttribute("email", userList.get(0).getEmail());
		
		req.setAttribute("finishedTasks", String.format("%.0f", (numOfFinishedTasks / numOfTasks) * 100));
		req.setAttribute("inProgressTasks", String.format("%.0f", (numOfInProgressTasks / numOfTasks) * 100));
		req.setAttribute("unfinishedTasks", String.format("%.0f", (numOfFinishedTasks / numOfTasks) * 100));
		
		req.getRequestDispatcher("profile.jsp").forward(req, resp);
		
	}
	

}
