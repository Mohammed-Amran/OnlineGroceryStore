 package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.model.Product;


@Controller
public class HomeController {

	@RequestMapping("/")
	protected String webPageOpener(Model model, HttpServletRequest req) {
	
	//Instantiating a session object:	
	HttpSession session = req.getSession(true);	
	
	//Initiating the session for 3 hours
	session.setMaxInactiveInterval(10800); 
		
	
	//Creating vegetables list to store vegetable products in it:	
	List<Product> VegetablesProductsList = new ArrayList<>();
	
	//Initializing product object:
	//Vegetable Category's:
	Product v1 = new Product(1, "Lettuce", "Fresh, crisp lettuce perfect for salads, sandwiches, and healthy meals", 1000, "vegetables", "/images/vegetables/veg6.jpg");
	Product v2 = new Product(2, "Spinach", "Nutritious, tender spinach leaves rich in iron and perfect for cooking or salads", 1000, "vegetables", "/images/vegetables/veg2.jpg");	
	Product v3 = new Product(3, "Cabbage", "Crunchy and versatile cabbage, ideal for slaws, stir-fries, and hearty dishes", 1000, "vegetables", "/images/vegetables/veg3.jpg");	
	Product v4 = new Product(4, "Carrot", "Sweet and crunchy carrots, great for snacking, cooking, or juicing", 1000, "vegetables", "/images/vegetables/veg4.jpg");	
	Product v5 = new Product(5, "Pepper", "Vibrant and flavorful peppers, perfect for adding color and spice to any dish", 1000, "vegetables", "/images/vegetables/veg5.jpg");	
	Product v6 = new Product(6, "Lemon", "Zesty and juicy lemons, ideal for adding a refreshing tang to food and drinks", 1500, "vegetables", "/images/vegetables/veg1.jpg");	
	Product v7 = new Product(7, "Cucumber", "Cool and crisp cucumbers, perfect for salads, snacks, and hydration", 1000, "vegetables", "/images/vegetables/veg7.jpg");	
	Product v8 = new Product(8, "Potato", "Hearty and versatile potatoes, perfect for boiling, baking, frying, or mashing", 1000, "vegetables", "/images/vegetables/veg8.jpg");	
	Product v9 = new Product(9, "Tomato", "Juicy and flavorful tomatoes, ideal for salads, sauces, and everyday cooking", 1000, "vegetables", "/images/vegetables/veg9.jpg");	
	Product v10 = new Product(10, "Broccoli", "Fresh and nutritious broccoli, great for steaming, roasting, or adding to stir-fries", 1250, "vegetables", "/images/vegetables/veg10.jpg");	
	
	VegetablesProductsList.add(v1); VegetablesProductsList.add(v2); VegetablesProductsList.add(v3); VegetablesProductsList.add(v4); VegetablesProductsList.add(v5);
	VegetablesProductsList.add(v6); VegetablesProductsList.add(v7); VegetablesProductsList.add(v8); VegetablesProductsList.add(v9); VegetablesProductsList.add(v10);	
	
	//saving the vegetable products list into session scope.	
	session.setAttribute("VegetablesProductsList", VegetablesProductsList);

//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 	
	
	//Creating fruits list to store fruit products in it:
	List<Product> FruitsProductsList = new ArrayList<>();
	
	//Fruits Category:
	Product f1 = new Product(11, "Apple", "Our apples are handpicked for their crispness and naturally sweet flavor, delivering a refreshing bite that keeps customers coming back", 1000, "fruits", "/images/fruits/apple.jpg");
	Product f2 = new Product(12, "Watermelon", "Our watermelons are sun-ripened and bursting with juicy sweetness—perfectly refreshing for hot days and packed with natural hydration", 6000, "fruits", "/images/fruits/watermelon.jpg");	
	Product f3 = new Product(13, "Orange", "Our oranges are vibrant, naturally sweet, and rich in vitamin C—ideal for a daily immune boost and a burst of refreshing citrus flavor", 1500, "fruits", "/images/fruits/orange.jpg");	
	Product f4 = new Product(14, "Plum", "Our plums are juicy, perfectly ripe, and bursting with sweet-tart flavor—an antioxidant-rich treat that’s as healthy as it is delicious", 2500, "fruits", "/images/fruits/plum.jpg");	
	Product f5 = new Product(15, "Grapes", "Our grapes are crisp, sweet, and seedless—perfect for snacking, packed with nutrients, and freshly picked for peak flavor", 2500, "fruits", "/images/fruits/grapes.jpg");	
	Product f6 = new Product(16, "Strawberry", "Our strawberries are vibrantly red, lusciously sweet, and handpicked at peak ripeness—ideal for desserts, smoothies, or a healthy snack", 6000, "fruits", "/images/fruits/starwberry.jpg");	
	Product f7 = new Product(17, "Peach", "Our peaches are irresistibly juicy with a velvety skin and a rich, sweet flavor—perfect for enjoying fresh, baking into pies, or blending into smoothies", 2250, "fruits", "/images/fruits/peach.jpg");	
	Product f8 = new Product(18, "Cherry", "Our cherries are bursting with natural sweetness and a hint of tartness, handpicked for peak freshness and perfect for snacking, desserts, or garnishing your favorite dishes", 10000, "fruits", "/images/fruits/cherry.jpg");	
	Product f9 = new Product(19, "Banana", "Our bananas are naturally sweet, with a smooth, creamy texture that melts in your mouth. Handpicked at the peak of ripeness, they're perfect for snacking, adding to smoothies, or enhancing your favorite baked goods", 1500, "fruits", "/images/fruits/banana.jpg");	
	Product f10 = new Product(20, "Fig", "Sweet and chewy figs, a delicious treat packed with natural fiber and nutrients", 12000, "fruits", "/images/fruits/fig.jpg");	

	FruitsProductsList.add(f1); FruitsProductsList.add(f2); FruitsProductsList.add(f3); FruitsProductsList.add(f4); FruitsProductsList.add(f5);
	FruitsProductsList.add(f6); FruitsProductsList.add(f7); FruitsProductsList.add(f8); FruitsProductsList.add(f9); FruitsProductsList.add(f10);
		
	//saving the fruit products list into session scope.	
	session.setAttribute("FruitsProductsList", FruitsProductsList);

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
