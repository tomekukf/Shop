package com.tomek.domek.service;

import java.util.List;
import java.util.Optional;

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

	public boolean isUserPresent(String email) {
		User user = userRepo.findByEmail(email);
		if (user != null)
			return true;

		return false;
	}

	public List<User> findThem(String username) {
		List<User> userList = userRepo.findAllByUsername(username);
		
		return userList;
		
	}
	
	public List<User> findByUsername(String name) {
		return  userRepo.findByUsernameLike("%"+name+"%");
	}

	public User findUserByEmail(String email) {
		
		User user = userRepo.findByEmail(email);
		return user;
	}

	
	
	

}
