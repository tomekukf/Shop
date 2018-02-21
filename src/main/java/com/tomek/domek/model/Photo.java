package com.tomek.domek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	
	private String photoKey;
	
	@Lob
	private byte[] photo;
	
	@OneToOne(mappedBy="photoKey")
	private Product product;
	

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Photo(String photoKey, byte[] photo, Product product) {
		super();
		this.photoKey = photoKey;
		this.photo = photo;
		this.product = product;
	}

	public Photo(String key) {
		super();
		this.photoKey = key;
	}

	public String getPhotoKey() {
		return photoKey;
	}

	public void setPhotoKey(String photoKey) {
		this.photoKey = photoKey;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
}
