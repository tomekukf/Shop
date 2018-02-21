package com.tomek.domek.controller;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tomek.domek.model.User;
import com.tomek.domek.repository.PhotoRepository;
import com.tomek.domek.service.PhotoService;
import com.tomek.domek.service.ProductService;
import com.tomek.domek.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	@Autowired
	private PhotoService photoService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private PhotoRepository photoRepository;
	
	
	

	@RequestMapping("/")
	public String getHome(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("products", productService.getAll());
		return "home";
	}
	
	
	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String id) throws IOException {

		byte[] media = photoService.getPhotoFromDB(id);

		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	
	
	
	@ResponseBody
	@GetMapping("/users/{user}")
	public String showUser(@PathVariable String user, Model model) {

		User user1 = userService.getUserByUsername(user);
		model.addAttribute("user", user1);

		return "messae" + user1;
	}
	
	@RequestMapping("/orderByPrice")
	public String getAllOrderByPrice(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("products", productService.getAllAndOrderByPrice());
		return "home";
	}
	@RequestMapping("/orderByBrand")
	public String getAllOrderByBrand(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("products", productService.getAllAndOrderByBrand());
		return "home";
	}
	
	
	

}
