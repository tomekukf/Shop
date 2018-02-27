package com.tomek.domek.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tomek.domek.model.User;
import com.tomek.domek.repository.UserRepository;
import com.tomek.domek.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;


	@GetMapping("/findUser")
	public String findUser(@RequestParam(defaultValue = "") String name, Model model) {
		
		List<User> userList = userService.findByUsername(name);
		
		if(userList!=null) {
		System.out.println("i foiund him"+userList);

		model.addAttribute("user", userList);
		}else {
			System.out.println("i did not found him");

		}
		
		
		return "views/user";

	}

}
