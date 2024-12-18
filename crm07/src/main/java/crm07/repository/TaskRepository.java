package crm07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.MysqlConfig;
import crm07.entity.StatusEntity;
import crm07.entity.TaskEntity;

public class TaskRepository {
	private StatusRepository statusRepository = new StatusRepository();
	
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
	
	public ArrayList<TaskEntity> findByUserId(int userId) {
		ArrayList<TaskEntity> taskList = new ArrayList<TaskEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT t.id, t.name, j.name 'job_name', u.fullname, t.start_date, t.end_date, s.name 'status_name' FROM tasks t "
					+ " JOIN jobs j ON t.job_id = j.id"
					+ " JOIN users u ON t.user_id = u.id"
					+ " JOIN status s ON t.status_id = s.id"
					+ "	WHERE u.id = ?";
		
		
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, userId);
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
			System.out.println("Find task by user id error" + e.getMessage());
		}	
		
		
		return taskList;
	}
	
	
	public ArrayList<TaskEntity> findByTaskID(String id) {
		ArrayList<TaskEntity> taskList = new ArrayList<TaskEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT t.id, t.name, j.name 'job_name', t.start_date, t.end_date, s.name 'status_name' FROM tasks t "
					+ " JOIN jobs j ON t.job_id = j.id"
					+ " JOIN status s ON t.status_id = s.id"
					+ "	WHERE t.id = ?";
		
		System.out.println(id);
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, id);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				TaskEntity task = new TaskEntity();
				task.setId(res.getInt("id"));
				task.setTaskName(res.getString("name"));
				task.setStartDate(res.getDate("start_date"));
				task.setEndDate(res.getDate("end_date"));
				task.setJobName(res.getString("job_name"));
				task.setStatus(res.getString("status_name"));
				taskList.add(task);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find task by id error" + e.getMessage());
		}	
		
		
		return taskList;
	}
	
	public int updateById(String id, int statusId) {
		
		Connection conn = MysqlConfig.getConnection();
		
		
		String query = "UPDATE tasks SET "
					 + "status_id = ? "
					 + "WHERE id = ?";
		int rowUpdated = 0;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, statusId);
			prepStatement.setInt(2, Integer.parseInt(id));
			
			rowUpdated = prepStatement.executeUpdate();

		} 
		catch(Exception e) {
			System.out.println("Update task by id error " + e.getMessage());
		}
		
		return rowUpdated;
	}
	
	public int insertTask(int jobId, String taskName, int userId, String startDate, String endDate) {
		Connection conn = MysqlConfig.getConnection();
		String query = "INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";
		int rowInserted = 0;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, taskName);
			prepStatement.setString(2, startDate);
			prepStatement.setString(3, endDate);
			prepStatement.setInt(4, userId);
			prepStatement.setInt(5, jobId);
			prepStatement.setInt(6, 1);
			
			rowInserted = prepStatement.executeUpdate();

		} 
		catch(Exception e) {
			System.out.println("insert task error " + e.getMessage());
		}
		
		return rowInserted;
		
	}
	
	public ArrayList<TaskEntity> findByJobID(int jobid, int userid) {
		ArrayList<TaskEntity> taskList = new ArrayList<TaskEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT t.id, t.name, j.name 'job_name', u.fullname, t.start_date, t.end_date, s.name 'status_name' FROM tasks t "
					+ " JOIN jobs j ON t.job_id = j.id"
					+ " JOIN status s ON t.status_id = s.id"
					+ " JOIN users u ON u.id = t.user_id"
					+ "	WHERE j.id = ? AND u.id = ?";
		
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, jobid);
			prepStatement.setInt(2, userid);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				TaskEntity task = new TaskEntity();
				task.setId(res.getInt("id"));
				task.setTaskName(res.getString("name"));
				task.setStartDate(res.getDate("start_date"));
				task.setEndDate(res.getDate("end_date"));
				task.setJobName(res.getString("job_name"));
				task.setUser(res.getString("fullname"));
				task.setStatus(res.getString("status_name"));
				taskList.add(task);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find task by job id and user id error" + e.getMessage());
		}	
		
		
		return taskList;
	}
	
	public int deleteByJobId(int jobId) {
		Connection conn = MysqlConfig.getConnection();
		String query = "DELETE FROM tasks t WHERE t.job_id = ?";
		int rowDeleted = 0;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, jobId);
			
			
			rowDeleted = prepStatement.executeUpdate();

		} 
		catch(Exception e) {
			System.out.println("delete task by job id error " + e.getMessage());
		}
		
		return rowDeleted;
		
	}
	
	public int deleteByTaskId(int taskId) {
		Connection conn = MysqlConfig.getConnection();
		String query = "DELETE FROM tasks t WHERE t.id = ?";
		int rowDeleted = 0;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, taskId);
			
			
			rowDeleted = prepStatement.executeUpdate();

		} 
		catch(Exception e) {
			System.out.println("delete task by task id error " + e.getMessage());
		}
		
		return rowDeleted;
		
	}

}
