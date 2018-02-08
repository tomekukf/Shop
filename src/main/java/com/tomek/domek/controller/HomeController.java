package com.tomek.domek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tomek.domek.service.ProductService;

@Controller
public class HomeController {
	
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String getHome(Model model) {
//		Product pr1 = new  Product("sukienka", "hm", 12.3, "mala,przeznaczona na ball");
model.addAttribute("products", productService.getAll());
		return "home";
	}
	
	
	
	

}
