package crm07.services;

import java.util.ArrayList;

import crm07.entity.JobEntity;
import crm07.entity.TaskEntity;
import crm07.entity.UserEntity;
import crm07.repository.JobRepository;
import crm07.repository.TaskRepository;
import crm07.repository.UserRepository;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	private JobRepository jobRepository = new JobRepository();
	private UserRepository userRepository = new UserRepository();
	
	public ArrayList<TaskEntity> getAllTasks() {
		return taskRepository.findAll();
		
	}
	
	public ArrayList<JobEntity> getAllJobs() {
		return jobRepository.findAll();
		
	}
	
	public ArrayList<UserEntity> getAllUsers() {
		return userRepository.findAll();
		
	}
	
	public boolean deleteTask(int id) {
		return taskRepository.deleteByTaskId(id) > 0;
	}
	
	public boolean insertATask(String jobname, String taskname, String username, String startdate, String enddate) {
		int jobId = jobRepository.findByName(jobname).getId();
		int userId = userRepository.findByName(username).getId();
		
		
		
		return taskRepository.insertTask(jobId, taskname, userId, startdate, enddate) > 0;
	}
	
	
}
