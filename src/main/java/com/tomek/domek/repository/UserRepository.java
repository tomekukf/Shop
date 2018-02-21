package com.tomek.domek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername ( String username);
	User findById ( int number);

}
