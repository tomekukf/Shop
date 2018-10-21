package com.tomek.domek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication  {

	public static void main(String[] args) {
		 SpringApplication.run(ShopApplication.class, args);
		  
//		 String[] list = context.getBeanDefinitionNames();
//		  for( int i=0; i<10;i++) {
//			  System.out.println(list[i]);
//			  
//			  
//		  }
////		  for(String name : context.getBeanDefinitionNames()) {
//		  System.out.println(name);
//		  }
	}}

//	@Autowired
//	private ProductRepository productRepository;

//	@Override
//	public void run(String... args) throws Exception {
//		
//		productRepository.save(new Product("Sukienka", "HM", 99.0, "opis", null,  new Photo("_DSC0444.jpg",  null, null), "_DSC0444.jpg"));
//		productRepository.save(new Product("Spodnie", "HM", 99.0, "opis", null,  new Photo("_DSC0444.jpg",  null, null), "_DSC0444.jpg"));
//		productRepository.save(new Product("Sukienka", "Reserved", 99.0, "opis", null,  new Photo("_DSC0444.jpg",  null, null), "_DSC0444.jpg"));
//	}
//
//}
