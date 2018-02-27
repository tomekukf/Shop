package com.tomek.domek.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User  implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotEmpty
//	@Column(unique=true)
	private String username;

	private String name;
	private String surname;

	@NotNull
	@Size(min = 5)
	private String password;

	@NotNull
	@Email
	@Column
	private String email;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLES", joinColumns = {
			@JoinColumn(name = "USER_EMAIL", referencedColumnName = "email") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_NAME", referencedColumnName = "role") })
	private Set<UserRole> roles ;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Product> products;

	public User(@NotNull String username, String name, String surname, @NotNull String password,
			@NotNull String email) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
	}

	public User(@NotEmpty String username, String name, String surname, @NotNull @Size(min = 5) String password,
			@NotNull @Email String email, List<Product> products) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.products = products;
	}

	public User(@NotEmpty String username, String name, String surname, @NotNull @Size(min = 5) String password,
			@NotNull @Email String email, Set<UserRole> roles, List<Product> products) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.products = products;
	}
	
	

	public User( String username,  String email) {
		this.username = username;
		this.email = email;
	}

	public User() {
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return username;
	}



	
	

}
