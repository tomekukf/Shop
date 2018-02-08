package com.tomek.domek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String role;
	
	private String description;

	
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserRole( String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}