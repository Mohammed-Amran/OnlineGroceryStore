package com.example.controller;


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
public class checkoutController {

	@PostMapping("/checkoutOrder")
	protected String checkoutOrders(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		
		//getting the 'selectedCity' & the 'Address' inputs from the user:
		String selectedCity = input.get("selectedCity");
		String Address = input.get("Address");
		
		
		//Instantiating a session object:
		HttpSession session = req.getSession(false);
		
		
		//saving the 'selectedCity' & the 'Address' into the session scope:
		session.setAttribute("selectedCity", selectedCity);
		session.setAttribute("Address", Address);
		
		
		//Retrieving the cartItems list from the session object:
		@SuppressWarnings("unchecked")
		List<CartItem> retrievedCartItems = (List<CartItem>) session.getAttribute("cartItemsList");
		
		
		//Retrieving the orderedItems list from the session object:		
		@SuppressWarnings("unchecked")
		List<CartItem> orderedItems = (List<CartItem>) session.getAttribute("orderedItems");
		
		
		//copying the cartItems into the orderedItems list:
		for(CartItem product : retrievedCartItems) {
			
			
			orderedItems.add(product);
			
		}
		
		
		//saving the 'orderedItems' list into the session scope:
		session.setAttribute("orderedItems", orderedItems);
		
		
		//Clearing out the 'cartItemsList' - since after checking out the cart must be cleared out:
		retrievedCartItems.clear();
		
		//Re-saving the 'cartItemsList' into the session scope again:
		session.setAttribute("cartItemsList", retrievedCartItems);
		
		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
		
		//Retrieving the 'inboxCounter' variable from the session scope:
		int inboxCounter = (Integer) session.getAttribute("inboxCounter");
		
		//Incrementing the 'inboxCounter' by 1 - since an order has been checkout!
		++inboxCounter;
		
		//Re-saving the 'inboxCounter' variable into the session scope again:
		session.setAttribute("inboxCounter", inboxCounter);
		
		//forwarding & wrapping the 'inboxCounter' variable into the request scope:
		model.addAttribute("inboxCounter", inboxCounter);
		
		
		
		
		//Retrieving the 'cartCounter' variable from the session scope:
		int cartCounter = (Integer) session.getAttribute("cartCounter");
		
		//re-initializing it to 0 - since after checking out, the cart should be cleared out:
		cartCounter = 0;
		
		//Re-saving the 'cartCounter' variable into the session scope again:
		session.setAttribute("cartCounter", cartCounter);
		
		//forwarding & wrapping the 'cartCounter' variable into the request scope:
		model.addAttribute("cartCounter", cartCounter);
		
		//This code below allows to represent the vegetable products as soon as the gets back to the home page again after checking out:
		String showCategory = "vegetable";
     	model.addAttribute("showCategory", showCategory);
		
		
		return "view/home";
		
	}//closing brace of the 'checkoutOrders()' method.
	
	
	
	@GetMapping("/accessCheckoutPage")
	protected String accessChecckoutPage(HttpServletRequest req, Model model) {
		
		//instantiating a session object:
		HttpSession session = req.getSession(false);
		
		//Retrieving the orderedItems list from the session scope:
		@SuppressWarnings("unchecked")
		List<CartItem> orderedItems = (List<CartItem>) session.getAttribute("orderedItems");
		
		//forwarding & wrapping the 'orderedItems' into the request scope:
		model.addAttribute("orderedItems", orderedItems);
		
		
		//Retrieving the Address info from the session scope:
		String selectedCity = (String) session.getAttribute("selectedCity");
		String Address = (String) session.getAttribute("Address");
		
		//forwarding & wrapping the Address info into the request scope:
		model.addAttribute("selectedCity", selectedCity);
		model.addAttribute("Address", Address);
		
		
		return "view/checkout";
		
	}//closing brace of the 'accessCheckoutPage()' method.
	
	
	
	@GetMapping("/getBackToHomeFromCheckout")
	protected String getBackToHomeFromCheckout(HttpServletRequest req, Model model) {
		
		String destination = "";
		
		//Instantiating a session object:
        HttpSession session = req.getSession(false);
		
        
        if(session != null) {
        	
        	//Retrieving the 'cartCounter' variable from the session scope:
    		int cartCounter = (Integer) session.getAttribute("cartCounter");
            
    		//forwarding & wrapping the 'cartCounter' variable into the request scope:
    		model.addAttribute("cartCounter", cartCounter);
    		
    		
    		//Retrieving the 'inobxCounter' variable from the session scope:
            int inboxCounter = (Integer) session.getAttribute("inboxCounter"); 
            
            //forwarding & wrapping the 'cartCounter' variable into the request scope:
            model.addAttribute("inboxCounter", inboxCounter); 
             
            
            //This code below allows to represent the vegetable products as soon as the gets back to the home page again after checking out:
            String showCategory = "vegetable";
            model.addAttribute("showCategory", showCategory);
            
            destination = "view/home";
        }
        else {
        	
        	String SessionError = "Your session has Expired!";
        	model.addAttribute("SessionError", SessionError);
        	
        	destination = "view/error";
        	
        }
        
		
		return destination;
	}
	
	
	
}//closing brace of the class.
