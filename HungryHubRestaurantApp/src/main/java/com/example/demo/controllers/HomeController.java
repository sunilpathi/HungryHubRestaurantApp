package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.loginCredentials.AdminLogin;
import com.example.demo.services.ProductServices;
import com.example.demo.services.UserServices;

@Controller
public class HomeController {
	
	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private UserServices userServices;
	
	@GetMapping(value = {"/home", "/"})
	public String home()
	{
		return "Home";
	}

	@GetMapping("/products")
	public String products( Model model)
	{ 
		List<Product> allProducts = this.productServices.getAllProducts();
		model.addAttribute("products", allProducts);
		return "Products";
	}

	@GetMapping("/location")
	public String location()
	{
		return "Locate_us";
	}

	@GetMapping("/about")
	public String about()
	{
		return "About";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
	    model.addAttribute("user", new User());
	    return "Register";
	}

	@PostMapping("/register")
	public String processRegister(@ModelAttribute("user") User user) {

	    System.out.println("BEFORE SAVE: " + user);

	    userServices.addUser(user);   // 🔥 THIS LINE SAVES DATA

	    System.out.println("AFTER SAVE");

	    return "redirect:/login";
	}


	@GetMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("adminLogin",new AdminLogin());
		return "Login";
	}

}
