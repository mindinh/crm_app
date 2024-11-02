package crm07.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm07.entity.JobEntity;
import crm07.services.JobService;
import crm07.services.UserService;

@WebServlet(name="jobController", urlPatterns = {"/jobs", "/job-add"})
public class JobController extends HttpServlet {
	
	private JobService jobService = new JobService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/jobs":
				loadJobs(req, resp);
			break;
			case "/job-add":
				req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
				
			break;
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/job-add":
				addJobPost(req, resp);
				
			break;
		}
	}
	
	private void loadJobs(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<JobEntity> jobList = jobService.getAllJobs();
		
		req.setAttribute("jobList", jobList);
		req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		
	}
	
	
	private void addJobPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pJobName = req.getParameter("jobname");
		String pStartDate = req.getParameter("startdate");
		String pEndDate = req.getParameter("enddate");
		
		System.out.println(pStartDate + " " + pEndDate);
		
		jobService.insertJob(pJobName, pStartDate, pEndDate);
		
		resp.sendRedirect(req.getContextPath() + "/jobs");
		
	}
	
	
	
	
}
