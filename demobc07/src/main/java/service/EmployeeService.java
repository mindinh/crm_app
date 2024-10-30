package service;

import java.util.ArrayList;

import demobc07.EmployeeEntity;
import repository.EmployeeRepository;

public class EmployeeService {
	private EmployeeRepository employeeRepo = new EmployeeRepository();
	
	public ArrayList<EmployeeEntity> getAllEmployee() {
		
		return employeeRepo.findAll();
	}
	
}
 