package crm07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.MysqlConfig;
import crm07.entity.StatusEntity;


public class StatusRepository {
	
	public ArrayList<StatusEntity> findAll() {
		ArrayList<StatusEntity> statusList = new ArrayList<StatusEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT * FROM status s";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				StatusEntity status = new StatusEntity();
				status.setId(res.getInt("id"));
				status.setStatusName(res.getString("name"));
				
				statusList.add(status);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find all status error" + e.getMessage());
		}
		
		return statusList;
	}
	
	public ArrayList<StatusEntity> findByName(String name) {
		ArrayList<StatusEntity> statusList = new ArrayList<StatusEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT * FROM status s WHERE name = ?";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, name);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				StatusEntity status = new StatusEntity();
				status.setId(res.getInt("id"));
				status.setStatusName(res.getString("name"));
				
				statusList.add(status);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find status by name error" + e.getMessage());
		}
		
		return statusList;
	}
	
}
