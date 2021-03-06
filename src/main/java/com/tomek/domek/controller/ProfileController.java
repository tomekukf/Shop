package com.tomek.domek.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tomek.domek.model.User;
import com.tomek.domek.service.ProductService;
import com.tomek.domek.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private ProductService productSerice;

	@Autowired
	private UserService userService;
	
	Logger log = LoggerFactory.getLogger(ProfileController.class);
	
	
	@GetMapping("/profile")
	public String getProfilePage (Model model, Principal principal) {

//		String email = principal.getName();
//		String email = "tomek.ukf@gmail.com";
		String email1 = principal.getName();
		log.info(email1);
		
		User user= userService.findUserByEmail( email1);
		
		
	model.addAttribute("products", productSerice.findUserProducts(user));
		
		return "views/profile";
	
	}
	
	
	
	@ResponseBody
	@GetMapping("/users/{user}")
	public String showUser(@PathVariable String user, Model model) {

		User user1 = userService.getUserByUsername(user);
		model.addAttribute("user", user1);

		return "messae" + user1;
	}
	

}
