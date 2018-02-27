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
	private ProductRepository productRepo;

	@RequestMapping("/")
	public String getHome(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,

			@ModelAttribute("message") String message) {
		model.addAttribute("products", productRepo.findAll(PageRequest.of(page, size)));
		model.addAttribute("currentPage", page);
		return "home";
	}

	
	@GetMapping("/login")
		public String showLoginForm() {
			
		return "views/loginForm";
		}
	

	



}
