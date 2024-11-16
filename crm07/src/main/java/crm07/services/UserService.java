package crm07.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.RoleEntity;
import crm07.entity.TaskEntity;
import crm07.entity.UserEntity;
import crm07.repository.RoleRepository;
import crm07.repository.TaskRepository;
import crm07.repository.UserRepository;
import crm07.utils.MD5;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	private RoleRepository roleRepository = new RoleRepository();
	private TaskRepository taskRepository = new TaskRepository();
	
	public ArrayList<UserEntity> getAllUser() {
		return userRepository.findAll();
		
	}
	
	public boolean deleteUserById(int id) {
		return userRepository.deleteById(id) < 1 ? false : true;
		
	}
	
	public ArrayList<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
	}
	
	public boolean insertUser(String name, String email, String password, int roleId) {
		String md5Password = MD5.getMd5(password);
		
		return userRepository.insertUser(name, email, md5Password, roleId) > 0;
	}
	
	public void getUserDetails(HttpServletRequest req, HttpServletResponse resp, String id) throws ServletException, IOException {
		int userId = Integer.parseInt(id);
		
		UserEntity user = userRepository.findById(userId);
		ArrayList<TaskEntity> taskList = taskRepository.findByUserId(userId);
		
		ArrayList<TaskEntity> finishedTasks = new ArrayList<TaskEntity>();
		ArrayList<TaskEntity> inProgressTasks = new ArrayList<TaskEntity>();
		ArrayList<TaskEntity> unfinishedTasks = new ArrayList<TaskEntity>();
		
		double numOfTasks = taskList.size();
		
		for (TaskEntity t : taskList) {
			if (t.getStatus().equals("Đã hoàn thành")) {
				finishedTasks.add(t);
			}
			if (t.getStatus().equals("Chưa thực hiện")) {
				unfinishedTasks.add(t);
			}
			if (t.getStatus().equals("Đang thực hiện")) {
				inProgressTasks.add(t);
			}
		}
		
		double numOfFinishedTasks = finishedTasks.size(), 
				numOfInProgressTasks = inProgressTasks.size(), 
				numOfUnfinishedTasks = unfinishedTasks.size();

		req.setAttribute("user", user);
		req.setAttribute("finishedTasks", finishedTasks);
		req.setAttribute("unfinishedTasks", unfinishedTasks);
		req.setAttribute("inprogressTasks", inProgressTasks);
		req.setAttribute("numOfFinishedTasks", numOfFinishedTasks > 0 ? String.format("%.0f", (numOfFinishedTasks / numOfTasks) * 100) : 0);
		req.setAttribute("numOfUnFinishedTasks", numOfUnfinishedTasks > 0 ? String.format("%.0f", (numOfUnfinishedTasks / numOfTasks) * 100) : 0);
		req.setAttribute("numOfInProgressTasks", numOfInProgressTasks > 0 ? String.format("%.0f", (numOfInProgressTasks / numOfTasks) * 100) : 0);
		
		
	}
}
