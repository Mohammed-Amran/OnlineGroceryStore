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

import com.example.dao.DaoCartItem;
import com.example.dao.DaoOrders;

import com.example.model.Orders;

@Controller
public class checkoutController {

	@PostMapping("/checkoutOrder")
	protected String checkoutOrders(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		String destination = "";
		
		
		//Instantiating a session object:
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			
			//Retrieving the 'userId' from the session scope:
			int userId = (Integer) session.getAttribute("userId");
			
			//Getting the inputs from the user:
			String selectedCity = input.get("selectedCity");
			
			String Address = input.get("Address");
			
			String productName = input.get("productName");
			
			int productPrice = Integer.parseInt( input.get("productPrice") );
			
			int selectedQuantity = Integer.parseInt( input.get("selectedQuantity") );
			
			int productSumPrice = Integer.parseInt( input.get( "productSumPrice" ) );
			
			//Instantiating an object from the 'DaoOrders' class:
			DaoOrders daoOrdersObj = new DaoOrders();
			
			//Calling the 'insertIntoOrders()' method via the daoOrdersObj:
			boolean isOrderInserted = daoOrdersObj.insertIntoOrders(userId, productName, productPrice, selectedQuantity, productSumPrice, selectedCity, Address);
			
			if(isOrderInserted) {
								
				//Retrieving the new 'inboxCounter' variable via the daoOrdersObj:
				int inboxCounter = daoOrdersObj.getOrderedItemsCount(userId);
				
				//Re-saving the 'inboxCounter' into the session Scope:
				session.setAttribute("inboxCounter", inboxCounter);
				
				
				//Clearing the cart after checking out the items:
				
				//I.Instantiating an object from the 'DaoCartItem' class:
				DaoCartItem daoCartItemObj = new DaoCartItem();
				
				//II. Calling the 'clearCart()' method via the daoCartItemObj:
				daoCartItemObj.clearCart(userId);
				
				//III. Re-retrieving the 'cartCounter' variable via the daoCartItemObj:
				int cartCounter = daoCartItemObj.getCartItemCount(userId);
				
				//IV. Re-saving the 'cartCounter' variable into the session scope:
				session.setAttribute("cartCounter", cartCounter);
				
				
				//This code below allows to represent the vegetable products as soon as the gets back to the home page again after checking out:
				String showCategory = "vegetable";
		     	model.addAttribute("showCategory", showCategory);
				
				
				destination = "view/home";
			
			}
			else {
				
				String orderInsertionError = "Failed to Checkout Items!";
				
				model.addAttribute("orderInsertionError", orderInsertionError);
				
				destination = "view/error";
			}
			
			
			
			
		}
		else {
			
			String SessionError = "Sorry! your session has expired";
			
			model.addAttribute("SessionError", SessionError);
			
			destination = "view/error";
			
		}
		
		
     	
		return destination;
		
	}//closing brace of the 'checkoutOrders()' method.
	
	
	
	
	
	@GetMapping("/accessCheckoutPage")
	protected String accessChecckoutPage(HttpServletRequest req, Model model) {
		
		String destination = "";
		
		//instantiating a session object:
		HttpSession session = req.getSession(false);
		
	
		if(session != null) {
			
			//Retrieving the orderedItems from the 'orders' table:
			
			//I.Retrieving the 'userId' from the session scope:
			int userId = (Integer) session.getAttribute("userId");
			
			//II. Instantiating an object from the 'DaoOrders' class:
			DaoOrders daoOrdersObj = new DaoOrders();
			
			//III. Accessing the 'getOrderedItemsByUserId()' method via the daoOrdersObj:
			List<Orders> orderedItems = (List<Orders>)  daoOrdersObj.getOrderedItemsByUserId(userId);
			
			//IV. Saving the 'orderedItems' into the session scope:
			session.setAttribute("orderedItems", orderedItems);
			
			
			destination = "view/checkout";
		
		}
		else {
			
            String SessionError = "Sorry! your session has expired";
			
			model.addAttribute("SessionError", SessionError);
			
			destination = "view/error";
		}
	
		
		return destination;
		
	}//closing brace of the 'accessCheckoutPage()' method.
	
	
	
		
	
	@GetMapping("/getBackToHomeFromCheckout")
	protected String getBackToHomeFromCheckout(HttpServletRequest req, Model model) {
		
		String destination = "";
		
		//Instantiating a session object:
        HttpSession session = req.getSession(false);
		
        
        if(session != null) {

        	
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
