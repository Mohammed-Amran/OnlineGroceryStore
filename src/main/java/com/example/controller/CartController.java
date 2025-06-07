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
public class CartController {

	@GetMapping("/getBackToHomePage")
	protected String returnBackToHomePage(HttpServletRequest req, Model model) {
		
		
		//Instantiating a session object:
		HttpSession session = req.getSession(false);
		
		
		//Retrieving the 'cartCounter' variable from the sessionScope:
		int cartCounter = (Integer) session.getAttribute("cartCounter");
		
		//forwarding & wrapping the 'cartCounter' into the request scope:
		model.addAttribute("cartCounter", cartCounter);
		
		//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
		
		//Retrieving the 'inboxCounter' variable from the sessionScope:
		int inboxCounter = (Integer) session.getAttribute("inboxCounter");
		
		//forwarding & wrapping the 'cartCounter' into the request scope:
        model.addAttribute("inboxCounter", inboxCounter);
		
        
      //-----------------------------------------------------------------------------------
        
        //Retrieving the 'productCategory' from the session scope:
         String productCategory = (String) session.getAttribute("productCategory");
         
        //if the selected product was already a vegetable-product,
        //show the vegetable products when the user gets back to the 'home' page
         if("vegetables".equals(productCategory)) {
      	
        	String showCategory = "vegetable";
         	model.addAttribute("showCategory", showCategory);
         }
         //else, if the selected product was already a fruit-product,
         //show the fruit products when the user gets back to the 'home' page
         else if("fruits".equals(productCategory)){
        	 
        	String showCategory = "fruit";
          	model.addAttribute("showCategory", showCategory);
         }
         
         
         
         
  	
		
		return "view/home";
	}
	
	
	@PostMapping("/addItemIntoCart")
	protected String addSelectedItemsIntoCart(@RequestParam Map<String, String> input,  HttpServletRequest req, Model model) {
		
		//Initializing a session object:
		HttpSession session  = req.getSession(false);
		
		//Retrieving the userId from the session scope:
		int userId = (Integer) session.getAttribute("userId");
	 
		
		//-----------------------------------------------------------------------------------
		//Retrieving the inputed items from the user:
		
		//I.productId:
		int productId = Integer.parseInt(input.get("productId"));
		
		
				
		//II.productName:
		String productName = input.get("productName");
		
		
		//III.selectedQuantity:
		int selectedQuantity = Integer.parseInt( input.get("selectedQuantity") );
		
		
		//IV.productPrice:
		int productPrice = Integer.parseInt( input.get("productPrice") );
		
		
		//V.productCategory:
		String productCategory = input.get("productCategory");
		
		
		//saving the 'productCategory' into the session scope:
		session.setAttribute("productCategory", productCategory);
		
		
		
		//calculating the 'productSumPrice':
		int productSumPrice = productPrice * selectedQuantity;
		
		
		
		//Retrieving the carItems list from the session Scope:
		@SuppressWarnings("unchecked")
		List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("cartItemsList");
			
		
		
		//Retrieving the cartCounter variable from the session Scope:
		int cartCounter = (Integer) session.getAttribute("cartCounter");
		
		
	
		
		//Instantiating an object from the 'CartItem' class & adding data into its object:
		CartItem cObj = new CartItem(userId, productId, productName, productPrice, selectedQuantity, productSumPrice);
		
		
		
		//I.Adding the 'cartItem' class object into the 'cartItemsList' list:
		cartItemsList.add(cObj);
		 
		
		
		
		//II. Incrementing the cartCounter by 1 - since an item have been added into the cart list!:
		cartCounter++;
		
		//III. Re-saving the 'cartItemsList' list & the 'cartCounter' variable into the session Scope again:
		session.setAttribute("cartItemsList", cartItemsList);
		session.setAttribute("cartCounter", cartCounter);
		
		
	    
		
		return "redirect:/getBackToHomePage"; 
		
	}//closing brace of the 'addSelectedItemsIntoCart()' method.


	
	@GetMapping("/incrementProductQuantity")
	protected String incrementProductQuantity(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		//I. get the 'productId' to Increment its Quantity by 1:
		int productId = Integer.parseInt( input.get("productId") );
		
		//II. Retrieving the 'cartItemsList' from the session &  Modify the Quantity for the specific product:
		
		//II-a: Instantiating a session object:
        HttpSession session = req.getSession(false);
		
        //II-b: Retrieving the 'cartItemsList' from the session scope: 
		@SuppressWarnings("unchecked")
		List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("cartItemsList");
		
		//II-c: Retrieving the 'cartCounter' variable from the session scope:
		int cartCounter = (Integer) session.getAttribute("cartCounter");
		
		for(CartItem cOBJ : cartItemsList) {
			
			if(productId == cOBJ.getProductId()) {
				
				int currentSelectedQuantity = cOBJ.getSelectedQuantity();
				
				++currentSelectedQuantity;
				
				cOBJ.setSelectedQuantity(currentSelectedQuantity);
				
				++cartCounter;
				
				break;
			}
		}
		
		//Re-saving the 'cartItemsList' & 'cartCounter' into the session scope again:
		session.setAttribute("cartItemsList", cartItemsList);
		session.setAttribute("cartCounter", cartCounter);
		
		//forwarding & wrapping the 'cartItemsList' & 'cartCounter' into the request scope
		model.addAttribute("cartItemsList", cartItemsList);
		model.addAttribute("cartCounter", cartCounter);
		
		
		return "view/cart";
		
	}//closing brace of the 'incrementProductQuantity' method
	
	
	@GetMapping("/decrementProductQuantity")
	protected String decrementProductQuantity(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		//I. get the 'productId' to Decrement its Quantity by 1:
		int productId = Integer.parseInt( input.get("productId") );
		
		
		//II. Retrieving the 'cartItemsList' from the session &  Modify the Quantity for the specific product:
		
		
				//II-a: Instantiating a session object:
		        HttpSession session = req.getSession(false);
				
		        
		        //II-b: Retrieving the 'cartItemsList' from the session scope: 
				@SuppressWarnings("unchecked")
				List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("cartItemsList");
				
				//II-c: Retrieving the 'cartCounter' variable from the session scope:
				int cartCounter = (Integer) session.getAttribute("cartCounter");
				
				for(CartItem cOBJ : cartItemsList) {
					
					if(productId == cOBJ.getProductId()) {
						
						int currentSelectedQuantity = cOBJ.getSelectedQuantity();
						
						if(currentSelectedQuantity == 1) {
							
							cartItemsList.remove(cOBJ);
							
							--cartCounter;
							
							break;
						}
						else {
							
							--currentSelectedQuantity;
							
							cOBJ.setSelectedQuantity(currentSelectedQuantity);
							
							--cartCounter;
							
							break;
						}
						
					}
				}
				
				//Re-saving the 'cartItemsList' & 'cartCounter' into the session scope again:
				session.setAttribute("cartItemsList", cartItemsList);
				session.setAttribute("cartCounter", cartCounter);
				
				//forwarding & wrapping the 'cartItemsList' & 'cartCounter' into the request scope
				model.addAttribute("cartItemsList", cartItemsList);
				model.addAttribute("cartCounter", cartCounter);
				
				
				return "view/cart";
		
		
		
	}//closing brace of the 'decrementProductQuantity' method
	
	
	
	@GetMapping("/removeProductFromCart")
	protected String removeFromCart(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		//I. get the 'productId' to Remove it from the Cart:
		int productId = Integer.parseInt( input.get("productId") );
		
		//II. Retrieving the 'cartItemsList' from the session &  Modify the Quantity for the specific product:
		
				//II-a: Instantiating a session object:
		        HttpSession session = req.getSession(false);
				
		        //II-b: Retrieving the 'cartItemsList' from the session scope: 
				@SuppressWarnings("unchecked")
				List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("cartItemsList");
				
				//II-c: Retrieving the 'cartCounter' variable from the session scope:
				int cartCounter = (Integer) session.getAttribute("cartCounter");
				
				for(CartItem cOBJ : cartItemsList) {
					
					if(productId == cOBJ.getProductId()) {
						
						cartItemsList.remove(cOBJ);
						
						--cartCounter;
						
						break;
					}
				}
				
				//Re-saving the 'cartItemsList' & 'cartCounter' into the session scope again:
				session.setAttribute("cartItemsList", cartItemsList);
				session.setAttribute("cartCounter", cartCounter);
				
				//forwarding & wrapping the 'cartItemsList' & 'cartCounter' into the request scope
				model.addAttribute("cartItemsList", cartItemsList);
				model.addAttribute("cartCounter", cartCounter);
				
			
				
				return "view/cart";
		
	}//closing brace of the 'removeProductFromCart' method
	
	
	
	
	@GetMapping("/accessCartPage")
	protected String accessCartPage(HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession(false);
		
		@SuppressWarnings("unchecked")
		List<CartItem> cartItemsList = (List<CartItem>) session.getAttribute("cartItemsList");
	
		
		
		model.addAttribute("cartItemsList", cartItemsList);
		
		return "view/cart";
	}
	
	
	@GetMapping("/getBackToHomeFromCart")
	protected String getBackToHomeFromCart(HttpServletRequest req, Model model) {
		
        String destination = "";
		
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			
			String showCategory = "vegetable";
			model.addAttribute("showCategory", showCategory);
			
			//Retrieving the 'cartCounter' variable from the session scope:
			int cartCounter = (Integer) session.getAttribute("cartCounter");
			
			//forwarding & wrapping the 'cartCounter' into the request Scope:
			model.addAttribute("cartCounter", cartCounter);
			
			
			//Retrieving the 'cartCounter' variable from the session scope:
			int inboxCounter = (Integer) session.getAttribute("inboxCounter");
			
			//forwarding & wrapping the 'cartCounter' into the request Scope:
			model.addAttribute("inboxCounter", inboxCounter);
			
			
			
			destination = "view/home";
		}
		else {
			
			//if the session has already expired - take the user back to the 'error' page
			
			//and show this message to it:
			model.addAttribute("SessionError", "Sorry, your session has been Expired");
			
			destination = "view/error";
		}
		

		
		return destination;
	}
	
	
	
}//closing brace of the class.
