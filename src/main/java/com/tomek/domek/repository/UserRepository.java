package com.tomek.domek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
//	User findByUsername ( String username);
	User findById ( int number);
	User findByEmail( String email);
	List<User> findAllByUsername(String username);
	User findByUsername(String username);
	
	List<User>	findAllByUsernameLike(String username);
	List<User> findByUsernameLike(String name); 
}
