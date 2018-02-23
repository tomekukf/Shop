package com.tomek.domek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tomek.domek.model.Photo;
import com.tomek.domek.model.Product;
import com.tomek.domek.repository.ProductRepository;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ShopApplication.class, args);

	}

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		
		productRepository.save(new Product("Sukienka", "HM", 99.0, "opis", null,  new Photo("_DSC0444.jpg",  null, null), "_DSC0444.jpg"));
	}

}
