package com.example.controller;

import javax.servlet.http.HttpServletRequest;
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
		 
		 		 
		return "view/home";
		
	}//closing brace of the 'homeCategoryController()' method.
	
	
	
	
}//closing brace of the class.
