package crm07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.MysqlConfig;
import crm07.entity.JobEntity;
import crm07.entity.RoleEntity;

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
	
}
