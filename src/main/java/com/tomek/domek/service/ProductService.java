package com.tomek.domek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomek.domek.model.Product;
import com.tomek.domek.model.User;
import com.tomek.domek.repository.ProductRepository;
import com.tomek.domek.repository.UserRepository;

@Service
public class ProductService {

	private static final String defaultUser = "tomi";
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired	
	private UserRepository userRepository;
	
	
	
	
	public void addProduct(Product product) {
		User user = userRepository.findByUsername(defaultUser);
		product.setUser(user);
		productRepo.save(product);
		
	}
	
	
	public List<Product> getAll(){
		
		List<Product> list = productRepo.findAll();
		return list;
	}
	
}
