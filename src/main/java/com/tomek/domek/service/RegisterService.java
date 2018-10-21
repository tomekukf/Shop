package com.tomek.domek.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tomek.domek.model.User;
import com.tomek.domek.model.UserRole;
import com.tomek.domek.repository.UserRepository;
import com.tomek.domek.repository.UserRoleRepository;

@Service
public class RegisterService {

	
//	private static final String defaultRole = "ROLE_USER";
	
	private UserRepository userRepo;
	
	private UserRoleRepository userRoleRepo;
	
	private EmailConfirmationService emailConfirmationService;
	
	private Logger looger = org.slf4j.LoggerFactory.getLogger(RegisterService.class);
	
	

	
	
	

@Autowired
	public void setEmailConfirmationService(EmailConfirmationService emailConfirmationService) {
		this.emailConfirmationService = emailConfirmationService;
	}



	@Autowired
	public void setUserRoleRepo(UserRoleRepository userRoleRepo) {
		this.userRoleRepo = userRoleRepo;
	}

	

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	
	
	
	
	public void createUser(User user) {
		BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
//		UserRole role = new UserRole("USER","adding products");
		Set<UserRole> roles= userRoleRepo.findByRole("USER");
		user.setRoles(roles);
		
		try {
			emailConfirmationService.sendEmailConfirmation(user.getEmail());
			
		} catch (MailException e) {
			looger.info("Error sending email " + e.getMessage());
		}
		userRepo.save(user);
		
		
	}
	



	public void createAdmin(User user) {
		BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		UserRole role = new UserRole("ADMIN","adding products,editing user posts");
		Set<UserRole> roles= new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		userRepo.save(user);
		
	}

	
//	public void registerNewUser(User user) {
//		UserRole role = userRoleRepo.findByRole(defaultRole);
//		user.getRoles().add(role);
//		userRepo.save(user);
//	}
//	
	
}
