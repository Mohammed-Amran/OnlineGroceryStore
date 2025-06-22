 package com.example.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dao.DaoProduct;
import com.example.model.Product;


@Controller
public class HomeController {

	@RequestMapping("/")
	protected String webPageOpener(Model model, HttpServletRequest req) {
	
	//Instantiating a session object:	
	HttpSession session = req.getSession(true);	
	
	//Initiating the session for 3 hours
	session.setMaxInactiveInterval(10800); 
		
	
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
	  
	  
	  


//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 	
	
	//This code below allows to show the vegetable products, as soon as the user access the Grocery Store web app.
	String showCategory = "vegetable";
	model.addAttribute("showCategory", showCategory);
	
	
		return "view/index";
		
	}//closing brace of the 'webPageOpener()' method
	
	
	
	
	@GetMapping("/indexPagecategoryController")
	protected String categoryController(HttpServletRequest req, Model model) {
		
		String category = req.getParameter("category");
		
		String showCategory = "";
		
		if("vegetable".equals(category)) {
			
			showCategory = category;
		}
		else if("fruit".equals(category)) {
			
			showCategory = category;
		}
		
		
		 model.addAttribute("showCategory", showCategory);
		
		return "view/index";
		
	}//closing brace of the 'categoryController' method.
	
	
	
	
	@GetMapping("/accessDetailsPage")
	protected String forwardUserToDetailsPage(HttpServletRequest req, Model model) {
		
		
		String destination = "";
		
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			
			@SuppressWarnings("unchecked")
			List<Product> VegetablesProductsList = (List<Product>) session.getAttribute("VegetablesProductsList");
			
			@SuppressWarnings("unchecked")
			List<Product> FruitsProductsList = (List<Product>) session.getAttribute("FruitsProductsList");
			
			model.addAttribute("FruitsProductsList", FruitsProductsList);
			model.addAttribute("VegetablesProductsList", VegetablesProductsList);
			
			
			//This code below allows to present the vegetable items as soon as the user access the 'details' page:
			String showCategory = "vegetable";
			model.addAttribute("showCategory", showCategory);
	
			 
			destination = "view/details";
		}
		else {
			
			model.addAttribute("SessionError", "Sorry, your session has been Expired");
			
			destination = "view/error";
		}
		

		
		return destination;
		
	}//closing brace of the 'forwardUserToDetailsPage()' method.
	
	
	
	
	@GetMapping("/detailsCategoryController")
	protected String detailsCategoryController(HttpServletRequest req, Model model) {
		
		String category = req.getParameter("category");
		
		String showCategory = "";
		
		if("vegetable".equals(category)) {
			
			showCategory = category;
		}
		else if("fruit".equals(category)) {
			
			showCategory = category;
		}
		
		
		 model.addAttribute("showCategory", showCategory);
		
		return "view/details";
		
	}//closing brace of the 'detailsCategoryController()' method.
	
	


	@GetMapping("/getBackToIndexFromDetails")
	protected String getBackUserToIndexPageFromDetailsPage(HttpServletRequest req, Model model) {
		
		
		    //This code below allows to represent the vegetable items as soon as the user access the index page: 
			String showCategory = "vegetable";
			model.addAttribute("showCategory", showCategory);
			
	
			
		return "view/index";
		
	}//closing brace of the 'getBackUserToIndexPageFromDetailsPage()' method.
	
	
	
	@GetMapping("/getBackToIndexFromError")
	protected String getBackToIndexFromError(HttpServletRequest req, Model model) {
		
		
		    //This code below allows to represent the vegetable items as soon as the user access the index page: 
			String showCategory = "vegetable";
			model.addAttribute("showCategory", showCategory);
			
	
			
		return "view/index";
		
	}//closing brace of the 'getBackUserToIndexPageFromDetailsPage()' method.
	
	
	
	
}//closing brace of the class.
