package crm07.services;

import java.util.ArrayList;

import crm07.entity.RoleEntity;
import crm07.repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();
	
	public ArrayList<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
		
	}
	
	public boolean addRole(String rolename, String roledesc) {
		return roleRepository.insertRole(rolename, rolename) > 0;
		
	}
}
