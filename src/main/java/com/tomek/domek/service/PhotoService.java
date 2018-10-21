package com.tomek.domek.service;

import java.io.File;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomek.domek.model.Photo;
import com.tomek.domek.model.Product;
import com.tomek.domek.repository.PhotoRepository;
import com.tomek.domek.repository.ProductRepository;


	
@Service
public class PhotoService {

	@Autowired
	private PhotoRepository photoRepository;
	@Autowired
	private ProductRepository productRepository;
//	@Autowired
//	private PhotoService photoSerivce;

	Logger log = LoggerFactory.getLogger(PhotoService.class);

	private final String pathname1 = "../photos/zebra.jpg";
	String key;

	public String takeKeyofProductPhoto(Product product) {
		Optional<Product> pr1 = productRepository.findById(product.getId());
		key = pr1.get().getPhotoKey().getPhotoKey();
		return key;

	}

	public File getFile() {
//		String pathname = "../photos/";
		File file = new File(pathname1);

		return file;
	}
	
	public byte[] getPhotoFromDB(String path) {
		Photo photo = photoRepository.findByPhotoKey(path);
		byte[] media;
		if(photo.getPhoto() != null ) {
			log.info("Jets foto");
		 media= photo.getPhoto();
		}else {
			media=null;
			System.out.println("brak zdjecia");
		}
		 
		
		return media;
	}


}
