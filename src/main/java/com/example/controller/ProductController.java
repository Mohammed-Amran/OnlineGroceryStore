package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

	@GetMapping("/HomecategoryController")
	protected String homeCategoryController(HttpServletRequest req, Model model) {
		
		String category = req.getParameter("category");
		
		String showCategory = "";
		
		if("vegetable".equals(category)) {
			
			showCategory = category;
		}
		else if("fruit".equals(category)) {
			
			showCategory = category;
		}
		
		
		 model.addAttribute("showCategory", showCategory);
		
		 //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		 
		 //Instantiating a session object
		 HttpSession session = req.getSession(false);
		 
		 //Retrieving the 'cartCounter' variable from the session scope:
		 int cartCounter = (Integer) session.getAttribute("cartCounter");
		 
		 //forwarding & wrapping the 'cartCounter' into the request scope:
		 model.addAttribute("cartCounter", cartCounter);
		 
		 
		//Retrieving the 'inboxCounter' variable from the session scope:
		 int inboxCounter = (Integer) session.getAttribute("inboxCounter");
		 
		//forwarding & wrapping the 'cartCounter' into the request scope:
		 model.addAttribute("inboxCounter", inboxCounter);
		 
		return "view/home";
		
	}//closing brace of the 'homeCategoryController()' method.
	
}//closing brace of the class.
