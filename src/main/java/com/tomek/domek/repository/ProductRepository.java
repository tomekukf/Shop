package com.tomek.domek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
