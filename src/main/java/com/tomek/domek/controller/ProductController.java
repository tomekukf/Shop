package com.tomek.domek.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tomek.domek.model.Product;
import com.tomek.domek.model.User;
import com.tomek.domek.repository.UserRepository;
import com.tomek.domek.service.PhotoService;
import com.tomek.domek.service.ProductService;
import com.tomek.domek.service.UserService;

@Controller
public class ProductController {

	// private static String uploadPath = "/Shop/src/main/resources/photos/";
	private static final String uploadPath1 = "C:\\Users\\Tomek\\Desktop\\Java 2018\\Workbench\\Shop\\src\\main\\resources\\photos\\";

	@Autowired
	private ProductService productService;

	@Autowired
	private PhotoService photoService;
	@Autowired
	private UserService userService;

	@Autowired
	private ServletContext servletContext;
	@Autowired
	private UserRepository userRepo;

	@PostMapping("/addProduct")
	public String addProduct(Model model, @Valid @ModelAttribute(name = "product") Product product,
			@RequestAttribute("file") MultipartFile file, RedirectAttributes atr, BindingResult results)
			throws IOException {

		if (results.hasErrors()) {
			return "NewProduct";
		} else {

			byte[] bytes = file.getBytes();

			productService.addProduct(product, file.getOriginalFilename(), bytes,
					userRepo.findByEmail("tomek.ukf@gmail.com"));

			String key = photoService.takeKeyofProductPhoto(product);
			Path path = Paths.get(uploadPath1 + key);

			Files.write(path, bytes);

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

}
