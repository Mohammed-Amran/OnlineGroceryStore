package com.example.controller;



import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.DaoCartItem;
import com.example.dao.DaoOrders;
import com.example.dao.DaoProduct;
import com.example.dao.DaoUser;
import com.example.model.Product;




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
		
		//Retrieving the inputs from the user:
		String email = input.get("email");
		String password = input.get("password");
		
		
		//Validating the inputed email:
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
			
			
			//Saving the email and password into the request Scope!
			//So that the user wont required to fill-in all the inputs again:		
			model.addAttribute("email", email);
			
			model.addAttribute("password", password);
			
			
			destination = "view/home";
		}
        else {
        	
        	//Checking if the user exists in the 'users' table:
        	
        	//I. Instantiating an object from the 'DaoUser' class:
        	DaoUser daoUserObj = new DaoUser();
        	
        	//II. Calling the 'CheckUser()' method via the daUserObj:
        	boolean daoUserExist = daoUserObj.CheckUser(email, password);
        	
        	if(daoUserExist) {
        		
        		//Instantiating a session object:
        		HttpSession session = req.getSession(true);
        		
        		//Extending the session for 2 more hours:
        		session.setMaxInactiveInterval(2 * 60 * 60);

        		
        		//Retrieving the userId from the 'users' table:
        		int userId = daoUserObj.retrieveId(email);
        		
        		//Retrieving the username from the 'users' table:
        		String username = daoUserObj.retrieveUserName(email, password);
        		
        		
        		//Saving all necessary info's into the session scope:
        		session.setAttribute("userId", userId);
        		
        		session.setAttribute("username", username);
        		
        		session.setAttribute("email", email);
        		
        		
        		//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
        		
        		
        		//Retrieving vegetables from 'products' table & storing it into the session Scope:	
        		
        		  //1.Instantiating an object from the 'DaoProduct' class:
        		  DaoProduct daoProductObj = new DaoProduct();
        		  
        		  try {
        			
        			//2.Calling the 'getVegetables()' via the daoProductObj & saving the vegetables into a list of type 'Product' class:
        			List<Product> VegetablesProductsList = daoProductObj.getVegetables();
        					
        			//3. Saving the vegetable products list into session scope.	
        			session.setAttribute("VegetablesProductsList", VegetablesProductsList);
        		}
        		catch (Exception e) {
        			
        			e.printStackTrace();
        		}

        		  
        	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 	
        		
        		  
        		//Retrieving fruits from 'products' table & storing it into the session Scope:	
        		  
        		  try {
        			
        			//2.Calling the 'getFruits()' via the daoProductObj & saving the fruits into a list of type 'Product' class:
        			List<Product> FruitsProductsList = daoProductObj.getFruits();
        			
        			//3. Saving the fruit products list into session scope.	
        			session.setAttribute("FruitsProductsList", FruitsProductsList);
        			
        		}
        		catch (Exception e) {
        			
        			e.printStackTrace();
        		} 
        		
        		//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        		
        		//Retrieving the cartCounter from the 'cartitems' table:
        		
        		//I.Instantiating an object from the 'DaoCartItem' class:
        		DaoCartItem daoCartItemObj = new DaoCartItem();
        		
        		//II. Calling the 'cartItemCounter()' method via the daoCartItemObj:
        		int cartCounter = daoCartItemObj.getCartItemCount(userId);
        		
        		//III. Saving the 'cartCounter' variable into the session Scope:
        		session.setAttribute("cartCounter", cartCounter);
        		
        		
        		//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
                
        		
        		//Retrieving the inboxCounter from the 'cartitems' table:
        		
        		//I.Instantiating an object from the 'DaoOrders' class:
        		DaoOrders daoOrdersObj = new DaoOrders();
        		
        		//II. Calling the 'cartItemCounter()' method via the daoCartItemObj:
        		int inboxCounter = daoOrdersObj.getOrderedItemsCount(userId);
        		
        		//III. Saving the 'cartCounter' variable into the session Scope:
        		session.setAttribute("inboxCounter", inboxCounter);
        		
        		
        		//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
        		
        		//This code below allows to show the vegetable products as soon as the user access the Grocery Store web app.
    	    	String showCategory = "vegetable";
    	    	model.addAttribute("showCategory", showCategory);
  
    	    	
    	    	destination = "view/home";
        		       		
        	}
        	else {
        		
        		String loginErrorMessage = "Failed to Login! Make Sure you are Authenticated";
        		
        		model.addAttribute("loginErrorMessage", loginErrorMessage);
        		
        		destination = "view/error";
        	
                 }

        	
	    }
	    
			    

		return destination;
		
	}//closing brace of the 'Login()' method.
	
	
	
	
	
	
	@GetMapping("/Logout")
	protected String logout(HttpServletRequest req, Model model) {
		
		
		//Instantiating a session object 
        HttpSession session = req.getSession(false); 
	  
	
        if(session != null) {
        	
        	
        	//Retrieving the user email from the session scope
    		String email = (String) session.getAttribute("email");
    		
    	
    		//1st: Instantiate an object from the 'DaoUsers' class:
    		DaoUser daoObj = new DaoUser();
    		
    		  
    		//2nd: Retrieve the userId via the 'retrieveId()' method:
            int userId =  daoObj.retrieveId(email);
        	
        	
        	//Destroying the session
            session.invalidate(); 
            
            
            //Instantiating an object from the 'daoCart' class.
            DaoCartItem daoClearObj = new DaoCartItem();
            
            //Clearing the all the items in the 'cartItems' table for the specific user via its userId using 'clearCart()' method:
            daoClearObj.clearCart(userId);
            
            
            //Saving a logOutMessage into the model object:
            String logOutMessage = "You have been logged out!";
            
            model.addAttribute("logOutMessage", logOutMessage);
                     
        }

       
        
        //Taking back the user to the 'login' page:
        return "view/login";

				
	}//closing brace of the 'logOutUser()' method.
		
		
		
		
		
	


}//closing brace of the class.
