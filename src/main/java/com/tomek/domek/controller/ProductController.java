package com.tomek.domek.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tomek.domek.model.Product;
import com.tomek.domek.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public String addProduct(Model model, @Valid @ModelAttribute(name = "product") Product product, BindingResult results) {

		if (results.hasErrors()) {
			return "NewProduct";
		} else {

			productService.addProduct(product);
			model.addAttribute("product", product);

		}
		return "redirect:/";
	}

	@GetMapping("/addProduct")
	public String addProductt(Model model) {
		model.addAttribute("product", new Product());
		return "NewProduct";
	}

}
