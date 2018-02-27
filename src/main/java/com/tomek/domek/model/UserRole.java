package com.tomek.domek.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class UserRole  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(length = 32)
	private String role;
	
	private String description;
	
	


	@ManyToMany(mappedBy="roles")
	private List<User> users;

	
	
	
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
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserRole(String role, String description, List<User> users) {
		super();
		this.role = role;
		this.description = description;
		this.users = users;
	}
	
	
	
	
}