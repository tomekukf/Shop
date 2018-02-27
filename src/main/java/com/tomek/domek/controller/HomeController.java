package com.tomek.domek.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tomek.domek.model.Product;
import com.tomek.domek.model.User;
import com.tomek.domek.repository.PhotoRepository;
import com.tomek.domek.repository.ProductRepository;
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

	@Autowired
	private ProductRepository productRepo;

	@RequestMapping("/")
	public String getHome(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,

			@ModelAttribute("message") String message) {
		model.addAttribute("products", productRepo.findAll(PageRequest.of(page, size)));
		model.addAttribute("currentPage", page);
		return "home";
	}

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

	@ResponseBody
	@GetMapping("/users/{user}")
	public String showUser(@PathVariable String user, Model model) {

		User user1 = userService.getUserByUsername(user);
		model.addAttribute("user", user1);

		return "messae" + user1;
	}

	@RequestMapping("/orderByPrice")
	public String getAllOrderByPrice(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("products", productRepo.findAllByOrderByBrandAsc());
		return "home";
	}

	@RequestMapping("/orderByBrand")
	public String getAllOrderByBrand(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("products", productService.getAllAndOrderByBrand());
		return "home";
	}

	@RequestMapping("/test")
	public String test(Model model) {

		return "test";

	}

}
