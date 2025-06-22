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
import com.example.dao.DaoProduct;
import com.example.model.CartItem;


@Controller
public class CartController {

	@GetMapping("/getBackToHomePage")
	protected String returnBackToHomePage(HttpServletRequest req, Model model) {
		
		
		//Instantiating a session object:
		HttpSession session = req.getSession(false);
		
    
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
		
		String destination = "";
		
		//Initializing a session object:
		HttpSession session  = req.getSession(false);
		
		//I. Retrieving the userId from the session scope:
		int userId = (Integer) session.getAttribute("userId");
	 
		
		//-----------------------------------------------------------------------------------
		//Retrieving the inputed items from the user:
				
			
		//II.selectedQuantity:
		int selectedQuantity = Integer.parseInt( input.get("selectedQuantity") );
		
		//III. productName:
		String productName = input.get("productName");
		
		//IV.productPrice:
		int productPrice = Integer.parseInt( input.get("productPrice") );
		
		
		//V.productId:
		int productId = Integer.parseInt(input.get("productId"));
		
		
		
		//Retrieving the 'productCategrory' and saving it into the session scope:
		String productCategory = input.get("productCategory");
		
		
		//saving the 'productCategory' into the session scope - (details are in: 'getBackToHomePage'):
		session.setAttribute("productCategory", productCategory);
		
		
		
		//calculating the 'productSumPrice':
		int productSumPrice = productPrice * selectedQuantity;
		
		
	   //Adding the item into the 'cartitems' table:
		
		//I. Instantiate an object from the 'DaoCartItem' class:
		DaoCartItem daoCartItemObj = new DaoCartItem();
		
		//II. Calling the 'InsertIntoCartItem()' method via the daoCartItemObj:
		boolean isItemAddedToCart = daoCartItemObj.insertIntoCartItem(userId, selectedQuantity, productSumPrice, productName, productPrice, productId);
		
		
	    if(isItemAddedToCart) {	    	
	    	
	    	//Retrieving the 'cartCounter' variable from the session scope:
	    	int cartCounter = (Integer) session.getAttribute("cartCounter");
	    	
	    	
	    	//Incrementing the cartCounter by 1. Since an item have been added into the cart list!:
	    	cartCounter++;
	    	
	    	//Re-saving the new 'cartCounter' variable into the session scope:
	    	session.setAttribute("cartCounter", cartCounter);
	    	
	    	
	    	destination = "redirect:/getBackToHomePage";
	    	
	    }
	    else {
	    	
	    	String addItemIntoCartFailureMessage = "Item Failed to added into the Cart!";
	    	
	    	model.addAttribute("addItemIntoCartFailureMessage", addItemIntoCartFailureMessage);
	    	
	    	destination = "view/error";
	    	
	    }
		
    
		
		return destination; 
		
	}//closing brace of the 'addSelectedItemsIntoCart()' method.


	
	
	
	@GetMapping("/incrementProductQuantity")
	protected String incrementProductQuantity(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		String destination = "";
		
		//Instantiating a session object:
        HttpSession session = req.getSession(false);
		
		//I. get the 'userId' from the session scope:
		int userId = (Integer) session.getAttribute("userId");
		
		//II. get the 'productId' to Increment its Quantity by 1:
		int productId = Integer.parseInt( input.get("productId") );
		
		//II.I - Instantiating an object from the 'DaoCartItem' class:
		DaoCartItem daoCartItemObj = new DaoCartItem();
		
		//II.II - Calling the 'IncrementUpdateCartItemQuantity()' via the daoCartItemObj:
		boolean isIncremented = daoCartItemObj.IncrementUpdateCartItemQuantity(userId, productId);
		
		if(isIncremented) {
		
			
			//Updating the 'productSumPrice' based on the new 'selectedQuantity':
			
			  //1st: Retrieve the 'productPrice' from 'products' table using the 'productId':
			   
			   //I. Instantiate an object from the 'DaoProduct' class:
			   DaoProduct daoProductObj = new DaoProduct();
			   
			   //II. Calling the 'retrieveProductPrice()' via the daoProductObj:
			   int productPrice = daoProductObj.retrieveProductPrice(productId);
			   
			   
			  //2nd: Retrieving the new 'selectedQuantity' from 'cartitems' table using the 'productId' & 'userId':
			
			   //I. Calling the 'retrieveSelectedQuantity()' method via the daoCartItemObj:
			   int newSelectedQuantity = daoCartItemObj.retrieveSelectedQuantity(userId, productId);
		
			   
			  //3rd: Calculating the new 'productSumPrice' based on the retrieved 'productPrice' & 'selectedQuantity':
			  int newProductSumPrice = productPrice * newSelectedQuantity;
			   
			  
			  //4th: Inserting the new 'productSumPrice' into the 'cartitems' table for the specific 'productId' & 'userId':
			  daoCartItemObj.updateProductSumPrice(userId, productId, newProductSumPrice);
			  
			  
		
			//II.III
			//Retrieving the new 'cartCounter' variable via the daoCartItemObj:
			int cartCounter = daoCartItemObj.getCartItemCount(userId);
		
			//Re-saving the 'cartCounter' variable into the session scope:
			session.setAttribute("cartCounter", cartCounter);
		
			
			//II.IV
			//Re-retrieving the 'cartItemsList' from the 'cartitems' table via the daoCartItemObj:
			List<CartItem> cartItemsList = daoCartItemObj.getCartItemsByUserId(userId);
			
			//Re-saving the 'cartItemsList' into the session scope again:
			session.setAttribute("cartItemsList", cartItemsList);
			
			
			destination = "view/cart";
		}
        else {
			
			String itemUpdatingErrorMessage = "Failed to increment item in the cart!";
			
			model.addAttribute("itemUpdatingErrorMessage", itemUpdatingErrorMessage);
			
			destination = "view/error";
		}
			
		
		return destination;
		
	}//closing brace of the 'incrementProductQuantity' method
	
	
	
	@GetMapping("/decrementProductQuantity")
	protected String decrementProductQuantity(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		String destination = "";
		
		//Instantiating a session object:
        HttpSession session = req.getSession(false);
		
		//I. get the 'userId' from the session scope:
		int userId = (Integer) session.getAttribute("userId");
		
		//II. get the 'productId' to Increment its Quantity by 1:
		int productId = Integer.parseInt( input.get("productId") );
		
		//II.I - Instantiating an object from the 'DaoCartItem' class:
		DaoCartItem daoCartItemObj = new DaoCartItem();
		
		
		//II.II - Calling the 'decrementUpdateCartItemQuantity()' via the daoCartItemObj:
		boolean isDecremented = daoCartItemObj.decrementUpdateCartItemQuantity(userId, productId);
		
		
		if(isDecremented) {
			
			
			//Updating the 'productSumPrice' based on the new 'selectedQuantity':
			
			  //1st: Retrieve the 'productPrice' from 'products' table using the 'productId':
			   
			   //I. Instantiate an object from the 'DaoProduct' class:
			   DaoProduct daoProductObj = new DaoProduct();
			   
			   //II. Calling the 'retrieveProductPrice()' via the daoProductObj:
			   int productPrice = daoProductObj.retrieveProductPrice(productId);
			   
			   
			  //2nd: Retrieving the new 'selectedQuantity' from 'cartitems' table using the 'productId' & 'userId':
			
			   //I. Calling the 'retrieveSelectedQuantity()' method via the daoCartItemObj:
			   int newSelectedQuantity = daoCartItemObj.retrieveSelectedQuantity(userId, productId);
		
			   
			  //3rd: Calculating the new 'productSumPrice' based on the retrieved 'productPrice' & 'selectedQuantity':
			  int newProductSumPrice = productPrice * newSelectedQuantity;
			   
			  
			  //4th: Inserting the new 'productSumPrice' into the 'cartitems' table for the specific 'productId' & 'userId':
			  daoCartItemObj.updateProductSumPrice(userId, productId, newProductSumPrice);
			
			
			//II.III
			//Retrieving the new 'cartCounter' variable via the daoCartItemObj:
			int cartCounter = daoCartItemObj.getCartItemCount(userId);
		
			//Re-saving the 'cartCounter' variable into the session scope:
			session.setAttribute("cartCounter", cartCounter);
		
			
			//II.IV
			//Re-retrieving the 'cartItemsList' from the 'cartitems' table via the daoCartItemObj:
			List<CartItem> cartItemsList = daoCartItemObj.getCartItemsByUserId(userId);
			
			//Re-saving the 'cartItemsList' into the session scope again:
			session.setAttribute("cartItemsList", cartItemsList);
			
			
			destination = "view/cart";
		}
         else {
			
			String itemUpdatingErrorMessage = "Failed to decrement item in the cart!";
			
			model.addAttribute("itemUpdatingErrorMessage", itemUpdatingErrorMessage);
			
			destination = "view/error";
		}
		
		
		return destination;
		
		
	}//closing brace of the 'decrementProductQuantity' method
	
	
	
	@GetMapping("/removeProductFromCart")
	protected String removeFromCart(@RequestParam Map<String, String> input, HttpServletRequest req, Model model) {
		
		String destination = "";
		
		//Instantiating a session object:
        HttpSession session = req.getSession(false);
		
		//I. get the 'userId' from the session scope:
		int userId = (Integer) session.getAttribute("userId");
		
		//II. get the 'productId' to Increment its Quantity by 1:
		int productId = Integer.parseInt( input.get("productId") );
		
		//II.I - Instantiating an object from the 'DaoCartItem' class:
		DaoCartItem daoCartItemObj = new DaoCartItem();
		
		
		//II.II - Calling the 'decrementUpdateCartItemQuantity()' via the daoCartItemObj:
		boolean isRemoved = daoCartItemObj.removeCartItem(userId, productId);
		
		
		if(isRemoved) {
			
			
			//II.III
			//Retrieving the new 'cartCounter' variable via the daoCartItemObj:
			int cartCounter = daoCartItemObj.getCartItemCount(userId);
		
			//Re-saving the 'cartCounter' variable into the session scope:
			session.setAttribute("cartCounter", cartCounter);
		
			
			//II.IV
			//Re-retrieving the 'cartItemsList' from the 'cartitems' table via the daoCartItemObj:
			List<CartItem> cartItemsList = daoCartItemObj.getCartItemsByUserId(userId);
			
			//Re-saving the 'cartItemsList' into the session scope again:
			session.setAttribute("cartItemsList", cartItemsList);
			
			
			destination = "view/cart";
		}
		else {
			
			String itemUpdatingErrorMessage = "Failed to remove item from the cart!";
			
			model.addAttribute("itemUpdatingErrorMessage", itemUpdatingErrorMessage);
			
			destination = "view/error";
		}
				
			
				
		return destination;
		
	}//closing brace of the 'removeProductFromCart' method
	
	
	
	
	
	
	
	@GetMapping("/accessCartPage")
	protected String accessCartPage(HttpServletRequest req, Model model) {
		
		//Instantiating a session object:
		HttpSession session = req.getSession(false);
		
		
		//Retrieving the 'userId' from the session scope:
		int userId = (Integer) session.getAttribute("userId");
	
		
		//Retrieving the cartItemsList from the 'cartitems' table:
		
		//I.Instantiating an object from the 'DaoCartItem' class:
		DaoCartItem daoCartItemObj = new DaoCartItem();
		
		//II. Accessing the 'getCartItemsByUserId()' method via the daoCartItemObj:
		List<CartItem> cartItemsList = daoCartItemObj.getCartItemsByUserId(userId);
		
		//III. Saving the 'carItemList' into the session Scope:
		session.setAttribute("cartItemsList", cartItemsList);
		
	
		return "view/cart";
	}
	
	
	
	@GetMapping("/getBackToHomeFromCart")
	protected String getBackToHomeFromCart(HttpServletRequest req, Model model) {
		
        String destination = "";
		
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			
			String showCategory = "vegetable";
			model.addAttribute("showCategory", showCategory);
			
					
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
