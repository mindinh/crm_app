package crm07.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.MysqlConfig;
import crm07.entity.RoleEntity;

public class RoleRepository {
	public ArrayList<RoleEntity> findAll() {
		ArrayList<RoleEntity> roleList = new ArrayList<RoleEntity>();
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT * FROM roles r";
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				RoleEntity role = new RoleEntity();
				role.setId(res.getInt("id"));
				role.setRoleName(res.getString("name"));
				role.setRoleDesc(res.getString("description"));
				
				roleList.add(role);
				
			}
			
		} 
		catch(Exception e) {
			System.out.println("Find all roles error" + e.getMessage());
		}
		
		return roleList;
		
	}
}
