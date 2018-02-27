package com.tomek.domek.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	
	 Set<UserRole> findByRole(String role);
}
