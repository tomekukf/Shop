package com.tomek.domek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomek.domek.model.User;
import com.tomek.domek.repository.UserRepository;

@Service
public class UserService {
@Autowired
	private UserRepository userRepo;


public User getUser(int number) {
	User user = userRepo.findById(number);
	return user;
}
public User getUserByUsername(String username) {
	User user = userRepo.findByUsername(username);
			return user;
}
	
	
}
