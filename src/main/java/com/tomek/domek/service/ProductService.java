package com.tomek.domek.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomek.domek.model.Photo;
import com.tomek.domek.model.Product;
import com.tomek.domek.model.User;
import com.tomek.domek.repository.PhotoRepository;
import com.tomek.domek.repository.ProductRepository;
import com.tomek.domek.repository.UserRepository;

@Service
public class ProductService {

	private static final String defaultUser = "tomi";

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhotoRepository photoRepository;

	public String photoAdress;

	public void addProduct(Product product, String name,byte[] file ) {
		User user = userRepository.findByUsername(defaultUser);
		product.setUser(user);

		product.setPhotoKey(generateKey(name));
		product.getPhotoKey().setPhoto(file);
		product.setAdress(photoAdress);
		productRepo.save(product);

	}

	private Photo generateKey(String name) {

		Photo photo = new Photo();
		String key = (int)(Math.random()*10)+"_" + name;
		photo.setPhotoKey(key);
		photoAdress = key;
		return photo;
	}

	public List<Product> getAll() {

		List<Product> list = productRepo.findAll();

		return list;
		
		
	}

	public List<Product> getAllAndOrderByPrice() {

		List<Product> list = productRepo.findAllByOrderByPriceAsc();
		return list;
		
}
	public List<Product> getAllAndOrderByBrand() {

		List<Product> list = productRepo.findAllByOrderByBrandAsc();
		return list;
}
}