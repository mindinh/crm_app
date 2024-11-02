package crm07.services;

import java.util.ArrayList;

import crm07.entity.TaskEntity;
import crm07.repository.TaskRepository;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	
	public ArrayList<TaskEntity> getAllTasks() {
		return taskRepository.findAll();
		
	}
}
