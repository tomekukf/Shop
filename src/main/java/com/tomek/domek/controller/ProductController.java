package com.tomek.domek.controller;

import java.io.IOException;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tomek.domek.model.Product;
import com.tomek.domek.repository.ProductRepository;
import com.tomek.domek.service.ProductService;

@Controller
public class ProductController {

	// private static String uploadPath = "/Shop/src/main/resources/photos/";
//	private static final String uploadPath1 = "C:\\Users\\Tomek\\Desktop\\Java 2018\\Workbench\\Shop\\src\\main\\resources\\photos\\";

	@Autowired
	private ProductService productService;

//	@Autowired
//	private PhotoService photoService;
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private ServletContext servletContext;
//	@Autowired
//	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;

	@PostMapping("/addProduct")
	public String addProduct(Model model, @Valid @ModelAttribute(name = "product") Product product,
			@RequestAttribute("file") MultipartFile file, RedirectAttributes atr, BindingResult results,
			Principal principal) throws IOException {

		if (results.hasErrors()) {
			return "NewProduct";
		} else {

			byte[] bytes = file.getBytes();
			String email = principal.getName();

			productService.addProduct(product, file.getOriginalFilename(), bytes, email);

			// String key = photoService.takeKeyofProductPhoto(product);
			// Path path = Paths.get(uploadPath1 + key);

			// Files.write(path, bytes);

			model.addAttribute("product", product);
			atr.addAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

		}
		return "redirect:/";
	}

	@GetMapping("/addProduct")
	public String addProductt(Model model) {
		model.addAttribute("product", new Product());
		return "NewProduct";
	}

	@ModelAttribute(name = "categoryValues")
	public String[] categoriesValues() {
		return new String[] { "Sukienka", "Spodnie", "Czapka" };

	}

	@ModelAttribute(name = "brandValues")
	public String[] brandValues() {
		return new String[] { "HM", "Bershka", "Stradivarius", "Reserved" };

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

}
