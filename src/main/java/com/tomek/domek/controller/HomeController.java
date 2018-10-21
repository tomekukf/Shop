package com.tomek.domek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tomek.domek.repository.ProductRepository;

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
