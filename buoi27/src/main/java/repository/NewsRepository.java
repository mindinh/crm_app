package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import config.JDBCConnection;
import entity.NewsEntity;

public class NewsRepository {
	
	public ArrayList<NewsEntity> findAll() {
		ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
		String query = "SELECT * FROM news";
		Connection conn = JDBCConnection.getConnection();
		
		try {
			PreparedStatement prepStatement = conn.prepareStatement(query);
			
			ResultSet res = prepStatement.executeQuery();
			
			
			while (res.next()) {
				NewsEntity n = new NewsEntity();
				n.setNews_title(res.getString("title"));
				n.setNews_content(res.getString("content"));
				n.setImg_url(res.getString("image_url"));
				
				newsList.add(n);
				
			}
			
			System.out.println("test " + newsList.size());
		} 
		catch(Exception e) {
			System.out.println("Find all error " + e.getMessage());
		}
		
		
		return newsList;
	}
	
}
