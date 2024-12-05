package crm07.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.JobEntity;
import crm07.entity.TaskEntity;
import crm07.entity.UserEntity;
import crm07.repository.JobRepository;
import crm07.repository.TaskRepository;
import crm07.repository.UserRepository;

public class JobService {
	private JobRepository jobRepository = new JobRepository();
	private TaskRepository taskRepository = new TaskRepository();
	private UserRepository userRepository = new UserRepository();
	
	public ArrayList<JobEntity> getAllJobs() {
		return jobRepository.findAll();
		
	}
	
	public boolean insertJob(String name, String start, String end) {
		String startDate = start.split("/")[2] + start.split("/")[1] + start.split("/")[0];
		String endDate = end.split("/")[2] + end.split("/")[1] + end.split("/")[0];
		
		return jobRepository.insertJob(name, startDate, endDate) > 0;
		
	}
	
	public void getJobDetails(HttpServletRequest req, HttpServletResponse resp, int jobid) throws ServletException, IOException {
		JobEntity job = new JobEntity();
		if (jobRepository.findById(jobid).size() > 0) {
			job = jobRepository.findById(jobid).get(0);
			
		}
		
		ArrayList<UserEntity> usersList = userRepository.findByJobID(jobid);
		Map<String, Map<String, List<TaskEntity>>> userTasks = new HashMap<>();

	    for (UserEntity u : usersList) {
	    	if (userTasks.containsKey(u.getFullname())) {
	    		continue;
	    	}
	        userTasks.putIfAbsent(u.getFullname(), new HashMap<>());
	        Map<String, List<TaskEntity>> taskStatusMap = userTasks.get(u.getFullname());

	        
	        taskStatusMap.putIfAbsent("Đang thực hiện", new ArrayList<>());
	        taskStatusMap.putIfAbsent("Chưa thực hiện", new ArrayList<>());
	        taskStatusMap.putIfAbsent("Đã hoàn thành", new ArrayList<>());

	        for (TaskEntity t : taskRepository.findByJobID(jobid, u.getId())) {
	        	
	            switch (t.getStatus()) {
	                case "Đang thực hiện":
	                    taskStatusMap.get("Đang thực hiện").add(t);
	                    break;
	                case "Chưa thực hiện":
	                    taskStatusMap.get("Chưa thực hiện").add(t);
	                    break;
	                case "Đã hoàn thành":
	                    taskStatusMap.get("Đã hoàn thành").add(t);
	                    break;
	                
	            }
	        }
	    }

	    double numOfTasks = 0, numOfFinishedTasks = 0, numOfInprogressTasks = 0, numOfPendingTasks = 0;
	    
	    for (String name : userTasks.keySet()) {
	        
	        Map<String, List<TaskEntity>> taskStatusMap = userTasks.get(name);
	        for (String status : taskStatusMap.keySet()) {
	        	numOfTasks += taskStatusMap.get(status).size();
	        	if (status.equals("Đang thực hiện")) {
	        		numOfInprogressTasks += taskStatusMap.get(status).size();
	        	}
	        	if (status.equals("Chưa thực hiện")) {
	        		numOfPendingTasks += taskStatusMap.get(status).size();
	        	}
	        	if (status.equals("Đã hoàn thành")) {
	        		numOfFinishedTasks += taskStatusMap.get(status).size();
	        	}
	            
	            
	        }
	    }

		
		req.setAttribute("job", job);
		req.setAttribute("userTasks", userTasks);
		req.setAttribute("finishedTasks", String.format("%.0f", (numOfFinishedTasks / numOfTasks) * 100));
		req.setAttribute("inProgressTasks", String.format("%.0f", (numOfInprogressTasks / numOfTasks) * 100));
		req.setAttribute("pendingTasks", String.format("%.0f", (numOfPendingTasks / numOfTasks) * 100));
		
		req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
		
	}
	
	public boolean deleteJob(int jobId) {
		return taskRepository.deleteByJobId(jobId) > 0 && jobRepository.deleteById(jobId) > 0;
		
	}
}
