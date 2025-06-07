package com.example.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.CartItem;



@Controller
public class LoginController {

	@GetMapping("/accessLoginPage")
	protected String provideAccessToLogin() {
		
		return "/view/login";
		
	}//closing brace of the 'provideAccessToLogin()' method.
	
	
	
	@GetMapping("/getBackToIndexPageFromLogin")
	protected String getBackToIndexPage(Model model) {
		
		
		String showCategory = "vegetable";
		model.addAttribute("showCategory", showCategory);
		
		return "view/index";
		
	}//closing brace of the 'getBackToIndexPage()' method.
	
	
	@PostMapping("/Login")
	protected String Login(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		String destination = "";
		
		String email = input.get("email");
		String password = input.get("password");
		
		
		
	    if( ( "testuser@gmail.com".equals(email) ) && ( "test12345".equals(password) ) ) {
	    	
	    	
	    	HttpSession session = req.getSession(true);
	    	
	    	session.setAttribute("email", email);
	    	session.setAttribute("userName", "testuser1");
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
	    	
	    	model.addAttribute("loginErrorMessage", "make sure your email & password are correct!");
	    	
	    	destination = "view/error";
	    }
		
	    

		return destination;
		
	}//closing brace of the 'Login()' method.
	
	
	@GetMapping("/Logout")
	protected String logout(HttpServletRequest req, Model model) {
		
		/* I know here am suppose to destroy the 'session'!
		   But if i do so! the vegetableProdutList & fruitProductList
		   which are currently saved in the session scope will be removed too!
		   so, till applying Database! I wont destroy the session when the user logs out!.
		*/
         
        //Saving a logOutMessage into the model object:
        String logOutMessage = "You have been logged out!";
            
        model.addAttribute("logOutMessage", logOutMessage);
         
 
        

        //Redirecting back the user to the 'login' page:
        return "view/login";
		
	}//closing brace of the 'logout()' method
	
	
	


}//closing brace of the class.
