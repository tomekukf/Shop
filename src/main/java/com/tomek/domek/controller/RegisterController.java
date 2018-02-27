package com.tomek.domek.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tomek.domek.model.User;
import com.tomek.domek.service.RegisterService;
import com.tomek.domek.service.UserService;

@Controller
public class RegisterController {

	private RegisterService registerService;

	private UserService userService;

	@Autowired
	public void setRegisterService(RegisterService registerService, UserService userService) {
		this.registerService = registerService;
		this.userService = userService;
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "userRegisterForm";
	}

	@PostMapping("/register")
	public String RegisterNewUser(Model model, @ModelAttribute @Valid User user, BindingResult results) {

		if (results.hasErrors()) {
			return "userRegisterForm";
		}

		if (userService.isUserPresent(user.getEmail())) {
			model.addAttribute("message", true);

			return "userRegisterForm";
		}
		
		registerService.createUser(user);

		model.addAttribute("registerMessage", "Registartion was successful");

		return "userRegisterForm";

	}

}
