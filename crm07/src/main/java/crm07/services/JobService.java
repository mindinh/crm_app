package crm07.services;

import java.util.ArrayList;

import crm07.entity.JobEntity;
import crm07.repository.JobRepository;

public class JobService {
	private JobRepository jobRepository = new JobRepository();
	
	public ArrayList<JobEntity> getAllJobs() {
		return jobRepository.findAll();
		
	}
}
