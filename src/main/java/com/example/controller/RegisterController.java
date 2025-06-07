package com.example.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CartItem;




@Controller
public class RegisterController {

	
	@GetMapping("/accessRegisterPage")
	protected String accessRegisterPageFromLogin() {
		
		return "view/register";
		
	}//closing brace of the 'accessRegisterPageFromLogin()' method
	
	
	@GetMapping("/getBackToLoginFromRegister")
	protected String getBackToLoginFromRegister() {
		
		return "view/login";
		
	}//closing brace of the 'getBackToLoginFromRegister()' method
	
	
	
	@PostMapping("/Register")
	protected String registerUser(@RequestParam Map<String, String> input, HttpServletRequest req, HttpServletResponse resp, Model model) {
		
		String destination = "";
		
		String userName = input.get("username");
		String email = input.get("email");
		String password = input.get("password");
		
		
		
	
		
		if( ("testuser1".equals(userName)) && ("testuser@gmail.com".equals(email)) && ("test12345".equals(password)) ) {
			 
			 
			    HttpSession session = req.getSession(false);
			    
			    session.setAttribute("userName", userName);
			    session.setAttribute("email", email);
			    session.setAttribute("userId", 1);
			  
			    List<CartItem> cartItemsList = new ArrayList<>();
			    List<CartItem> orderedItems = new ArrayList<>(); 
		    	
			    int cartCounter = 0;
			    int inboxCounter = 0;
		    	
		    	session.setAttribute("cartItemsList", cartItemsList);
		    	session.setAttribute("orderedItems", orderedItems);
			   
		    	session.setAttribute("cartCounter", cartCounter);
		    	session.setAttribute("inboxCounter", inboxCounter);
			    
			    model.addAttribute("cartCounter", cartCounter);
			    
			    //This code below allows to show the vegetable products as soon as the user access the Grocery Store web app.
			    String showCategory = "vegetable";
			  
			    model.addAttribute("showCategory", showCategory);
			    
			    			    
			
			    destination = "view/home";
		}
		else {
			 
			
			String registrationError = "Make sure that your email correct!";
			model.addAttribute("registrationError", registrationError);
			
				
			destination = "view/error";
		        
		    }

		
      return destination;	
				
	}//closing brace of the 'registerUser()' method
	
	
	
	
	
	
	
	
	
}//closing brace of the class.
