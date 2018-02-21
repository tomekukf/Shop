package com.tomek.domek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
	List<Product> findAllByBrand(String brand);
	List<Product> findAllByOrderByPriceAsc();
	List<Product> findAllByOrderByBrandAsc();
	
	

}
