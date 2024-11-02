package crm07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.MysqlConfig;
import crm07.entity.UserEntity;

public class UserRepository {
	public ArrayList<UserEntity> findByEmailAndPassword(String email, String password) {
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT * FROM users u JOIN roles r ON u.role_id = r.id WHERE u.email = ? AND u.password = ?";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, email);
			prepStatement.setString(2, password);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				UserEntity user = new UserEntity();
				user.setId(res.getInt("id"));
				user.setEmail(res.getString("email"));
				user.setRoleName(res.getString("name"));
				
				userList.add(user);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find user by email and password error " + e.getMessage());
		}
		
		
		
		
		return userList;
	}
	
	public ArrayList<UserEntity> findByEmail(String email) {
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();
		
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT * FROM users u WHERE u.email = ?";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, email);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				UserEntity user = new UserEntity();
				user.setId(res.getInt("id"));
				user.setEmail(res.getString("email"));
				user.setFullname(res.getString("fullname"));
				
				userList.add(user);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find user by email error " + e.getMessage());
		}
		
		
		
		
		return userList;
	}
	
	public ArrayList<UserEntity> findAll() {
		ArrayList<UserEntity> userList = new ArrayList<UserEntity>();

		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT u.id, u.fullname, u.email, r.name FROM users u JOIN roles r ON u.role_id = r.id";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				UserEntity user = new UserEntity();
				user.setId(res.getInt("id"));
				user.setEmail(res.getString("email"));
				user.setRoleName(res.getString("name"));
				user.setFullname(res.getString("fullname"));
				
				userList.add(user);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find user by email and password error " + e.getMessage());
		}
		
		
		
		return userList;
	}
	
	
	public int deleteById(int id) {
		Connection conn = MysqlConfig.getConnection();
		String query = "DELETE FROM users u WHERE u.id = ?";
		int rowDeleted = 0;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setInt(1, id);
			
			rowDeleted = prepStatement.executeUpdate();

		} 
		catch(Exception e) {
			System.out.println("Delete user by id error " + e.getMessage());
		}
		
		return rowDeleted;
	}
	
	public int insertUser(String name, String email, String password, int roleId) {
		Connection conn = MysqlConfig.getConnection();
		String query = "INSERT INTO users (email, password, fullname, role_id) VALUES (?, ?, ?, ?)";
		int rowInserted = 0;
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			prepStatement.setString(1, email);
			prepStatement.setString(2, password);
			prepStatement.setString(3, name);
			prepStatement.setInt(4, roleId);
			
			rowInserted = prepStatement.executeUpdate();

		} 
		catch(Exception e) {
			System.out.println("insert user error " + e.getMessage());
		}
		
		return rowInserted;
		
	}
}
