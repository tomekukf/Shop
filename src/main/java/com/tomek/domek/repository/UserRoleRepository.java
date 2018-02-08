package com.tomek.domek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	
	 UserRole findByRole(String role);
}
