package com.tomek.domek.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String category;

	private String brand;
	@NotNull
	private double price;

	private String description;

	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Photo photoKey;
	

	private String adress;

	
	


	public Photo getPhotoKey() {
		return photoKey;
	}





	public void setPhotoKey(Photo photoKey) {
		this.photoKey = photoKey;
	}





	public Product(@NotEmpty String category, String brand, @NotNull double price, String description, User user,Photo photoKey,String adress) {
		super();
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.user = user;
		this.photoKey = photoKey;
		this.adress=adress;
	
	}
	








	public String getAdress() {
		return adress;
	}





	public void setAdress(String adress) {
		this.adress = adress;
	}





	public Product() {
		super();
	}

	
	

	

	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getCategory() {
		return category;
	}

	public void setCategory(String name) {
		this.category = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", brand=" + brand + ", price=" + price + ", description="
				+ description + "]";
	}
	

}
