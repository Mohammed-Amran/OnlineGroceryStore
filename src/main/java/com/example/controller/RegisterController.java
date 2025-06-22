package com.example.controller;


import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.dao.*;




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
	
	
	
	@PostMapping("/registerUser")
	protected String registerUser(@RequestParam Map<String, String> input, HttpServletRequest req, HttpServletResponse resp, Model model) {
		
		String destination = "";
		
		//Retrieving the user inputs:
		String userName = input.get("username");
		
		String email = input.get("email");
		
		String password = input.get("password");
		
		
		//Authenticate the email:
		
	
		boolean isEmailValid = false;
		
		
		//Email Validation Reference:
		final Pattern EMAIL_REGEX =  Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
		
		
		//Checking if the inputed 'email' is Authenticated? or not!
		if(EMAIL_REGEX.matcher(email).matches()) {
			
			isEmailValid = true;
		}
		else {
			
			isEmailValid = false;
		}
	
		
		if(isEmailValid == false) {
			
			String emailError = "Make sure your email is Correct";
			
			model.addAttribute("emailError", emailError);
			
			
			//Saving the username, email and password into the request Scope!
			//So that the user wont required to fill-in all the inputs again:
			model.addAttribute("username", userName);
			
			model.addAttribute("email", email);
			
			model.addAttribute("password", password);
			
			
			destination = "view/home";
		}
		else{
			 
			    //Instantiating a session object:
			    HttpSession session = req.getSession(false);
			    
			    
			    //Inserting user into 'users' table:
			    
			    //I. Instantiating an object from 'DaoUsers' class:
			    DaoUser daoUsersObj = new DaoUser();
			    
			    //II. Calling the 'insertUser()' method via the daoUsersObj:
			    boolean isUserInserted = daoUsersObj.insertUser(userName, email, password);
			    
			    
			    if(isUserInserted) {
			    	
			    	//Retrieve the userId from the 'users' table:
			    	int userId = daoUsersObj.retrieveId(email);
			    	
			    	//Saving all the necessary info's into the session scope:
			    	session.setAttribute("userName", userName);
			    	
					session.setAttribute("email", email);
					
					session.setAttribute("userId", userId);
					
					
					//Retrieving the inboxCounter from 'orders' table:
					int inboxCounter = 0;
					
					
					//I. Instantiating an object from the 'DaoOrders' class:
					DaoOrders daoOrdersObj = new DaoOrders();
					
					//II. Calling the 'getOrderedItemsCount()' method via the daoOrdersObj:	
					inboxCounter = daoOrdersObj.getOrderedItemsCount(userId);
					
					//III. Saving the 'inboxCounter' variable into the session scope:
					session.setAttribute("inboxCounter", inboxCounter);
					
					
					 destination = "view/home";
			    	
			    }
		    
			    
			    //This code below allows to show the vegetable products as soon as the user access the Grocery Store web app.
			    String showCategory = "vegetable";
			  
			    model.addAttribute("showCategory", showCategory);
			    			   
		}
		
		
      return destination;	
				
	}//closing brace of the 'registerUser()' method
	
	
	
	
	
	
	
	
	
}//closing brace of the class.
