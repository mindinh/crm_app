package crm07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.MysqlConfig;
import crm07.entity.TaskEntity;

public class TaskRepository {
	public ArrayList<TaskEntity> findAll() {
		ArrayList<TaskEntity> taskList = new ArrayList<TaskEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT t.id, t.name, j.name 'job_name', u.fullname, t.start_date, t.end_date, s.name 'status_name' FROM tasks t "
					+ " JOIN jobs j ON t.job_id = j.id"
					+ " JOIN users u ON t.user_id = u.id"
					+ " JOIN status s ON t.status_id = s.id";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				TaskEntity task = new TaskEntity();
				task.setId(res.getInt("id"));
				task.setTaskName(res.getString("name"));
				task.setStartDate(res.getDate("start_date"));
				task.setEndDate(res.getDate("end_date"));
				task.setUser(res.getString("fullname"));
				task.setJobName(res.getString("job_name"));
				task.setStatus(res.getString("status_name"));
				
				taskList.add(task);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find all tasks error" + e.getMessage());
		}
		
		
		
		
		return taskList;
	}
	
	public ArrayList<TaskEntity> findByEmail(String email) {
		ArrayList<TaskEntity> taskList = new ArrayList<TaskEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT t.id, t.name, j.name 'job_name', u.fullname, t.start_date, t.end_date, s.name 'status_name' FROM tasks t "
					+ " JOIN jobs j ON t.job_id = j.id"
					+ " JOIN users u ON t.user_id = u.id"
					+ " JOIN status s ON t.status_id = s.id"
					+ "	WHERE u.email = ?";
		
		System.out.println(email);
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, email);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				TaskEntity task = new TaskEntity();
				task.setId(res.getInt("id"));
				task.setTaskName(res.getString("name"));
				task.setStartDate(res.getDate("start_date"));
				task.setEndDate(res.getDate("end_date"));
				task.setUser(res.getString("fullname"));
				task.setJobName(res.getString("job_name"));
				task.setStatus(res.getString("status_name"));
				
				taskList.add(task);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find task by email error" + e.getMessage());
		}	
		
		
		return taskList;
	}
	

}
