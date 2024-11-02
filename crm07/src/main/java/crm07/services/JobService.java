package crm07.services;

import java.util.ArrayList;

import crm07.entity.JobEntity;
import crm07.repository.JobRepository;

public class JobService {
	private JobRepository jobRepository = new JobRepository();
	
	public ArrayList<JobEntity> getAllJobs() {
		return jobRepository.findAll();
		
	}
	
	public boolean insertJob(String name, String start, String end) {
		String startDate = start.split("/")[2] + start.split("/")[1] + start.split("/")[0];
		String endDate = end.split("/")[2] + end.split("/")[1] + end.split("/")[0];
		
		return jobRepository.insertJob(name, startDate, endDate) > 0;
		
	}
}
