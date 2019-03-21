package com.engineering.medium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 
 * @author zeljko.jelicic
 * 
 * Login controller for MVC, this isn't used in this REST scenario
 *
 */

@Controller
public class LoginController {
	

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		return "index";
	}
	
	@GetMapping("/")
	public String showHome() {
		return "redirect:/showLoginPage";
	}
	
	
	
}
