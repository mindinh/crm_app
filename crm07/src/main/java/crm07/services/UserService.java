package crm07.services;

import java.util.ArrayList;

import crm07.entity.RoleEntity;
import crm07.entity.UserEntity;
import crm07.repository.RoleRepository;
import crm07.repository.UserRepository;
import crm07.utils.MD5;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	private RoleRepository roleRepository = new RoleRepository();
	
	public ArrayList<UserEntity> getAllUser() {
		return userRepository.findAll();
		
	}
	
	public boolean deleteUserById(int id) {
		return userRepository.deleteById(id) < 1 ? false : true;
		
	}
	
	public ArrayList<RoleEntity> getAllRoles() {
		return roleRepository.findAll();
	}
	
	public boolean insertUser(String name, String email, String password, int roleId) {
		String md5Password = MD5.getMd5(password);
		
		return userRepository.insertUser(name, email, md5Password, roleId) > 0;
	}
}
