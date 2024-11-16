package crm07.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.MysqlConfig;
import crm07.entity.JobEntity;


public class JobRepository {
	
	public ArrayList<JobEntity> findAll() {
		ArrayList<JobEntity> jobList = new ArrayList<JobEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT * FROM jobs j";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				JobEntity job = new JobEntity();
				job.setId(res.getInt("id"));
				job.setJobName(res.getString("name"));
				job.setStartDate(res.getDate("start_date"));
				job.setEndDate(res.getDate("end_date"));
				
				jobList.add(job);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find all jobs error" + e.getMessage());
		}
		
		
		return jobList;
	}
	
	public int insertJob(String name, String startDate, String endDate) {
		int rowInserted = 0;
		Connection conn = MysqlConfig.getConnection();
		String query = "INSERT INTO jobs (name, start_date, end_date) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			
			System.out.println("test " + startDate + " " + endDate);
			
			prepStatement.setString(1, name);
			prepStatement.setString(2, startDate);
			prepStatement.setString(3, endDate);
			
			rowInserted = prepStatement.executeUpdate();
			
			
		} 
		catch(Exception e) {
			System.out.println("Insert job error" + e.getMessage());
		}
		
		return rowInserted;
		
	}
	
	public JobEntity findByName(String name) {
		JobEntity job = new JobEntity();
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT * FROM jobs j WHERE j.name = ?";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, name);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				
				job.setId(res.getInt("id"));
				job.setJobName(res.getString("name"));
				job.setStartDate(res.getDate("start_date"));
				job.setEndDate(res.getDate("end_date"));
				
				
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find job by name error" + e.getMessage());
		}
		
		return job;
	}
}
