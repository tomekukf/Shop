package com.tomek.domek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tomek.domek.model.Product;
import com.tomek.domek.model.User;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
	List<Product> findAllByBrand(String brand);
	List<Product> findAllByOrderByPriceAsc();
	List<Product> findAllByOrderByBrandAsc();
	
	List<Product> findByUser(User user);
	
	
//	List<Product> findAllByUserrr(Long id );
//	Product findByUserid (long id);
//	List<Product> findByUser();
	
	

}
