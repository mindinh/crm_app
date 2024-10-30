package service;

import java.util.ArrayList;

import entity.NewsEntity;
import repository.NewsRepository;

public class NewsService {
	
	private NewsRepository newsRepository = new NewsRepository();
	
	public ArrayList<NewsEntity> getAllNews() {
		return newsRepository.findAll();
		
	}
	
}
