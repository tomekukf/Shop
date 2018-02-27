package com.tomek.domek.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tomek.domek.model.Product;
import com.tomek.domek.model.User;
import com.tomek.domek.service.PhotoService;

@Controller
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;
	
	@RequestMapping(value = "/lol/{photoID}/lol", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String photoID)
			throws IOException {
		System.out.println(photoID+"lolllll");
		byte[] media = photoService.getPhotoFromDB(photoID);

		// File nowe = new File("0_czapka.jpg");
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value = "/users/{userProducts}/users", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody ResponseEntity<byte[]> getImageAsResponseEntityy(@ModelAttribute User userProducts)
			throws IOException {

		List<Product> list = userProducts.getProducts();
		System.out.println(list);
		for (Product product : list) {
			System.out.println(product);
		}
//		Optional<Product> product = productService.findByUserid(userProducts);
//		List<Product> product = productService.findByUserid(userProducts);
		String  pathname="3_czapka.jpg";
		byte[] photo = photoService.getPhotoFromDB(pathname);

		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(photo, headers, HttpStatus.OK);
		return responseEntity;
	}

}
