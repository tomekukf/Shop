package com.tomek.domek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomek.domek.model.User;
import com.tomek.domek.model.UserRole;
import com.tomek.domek.repository.UserRepository;
import com.tomek.domek.repository.UserRoleRepository;

@Service
public class RegisterService {

	
	private static final String defaultRole = "ROLE_USER";
	
	private UserRepository userRepo;
	
	private UserRoleRepository userRoleRepo;
	
	
	@Autowired
	public void setUserRoleRepo(UserRoleRepository userRoleRepo) {
		this.userRoleRepo = userRoleRepo;
	}

	

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	

	
	public void registerNewUser(User user) {
		UserRole role = userRoleRepo.findByRole(defaultRole);
		user.getRoles().add(role);
		userRepo.save(user);
	}
	
	
}
