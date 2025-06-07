package com.example.model;

public class CartItem {

	//Instance variables:
	private int productId;
	private int userId;
	private String productName;
	private double productPrice;
	private int selectedQuantity;
	private int productSumPrice; // = (producctPrice * selectedQuantity)
	
	
	
	//Constructor
	public CartItem(int userId, int productId, String productName, double productPrice, int selectedQuantity, int productSumPrice) {
		
		this.productId = productId;
		this.userId = userId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.selectedQuantity = selectedQuantity;
		this.productSumPrice = productSumPrice;
		
	}//closing brace of the constructor
	
	
	public void setProductPrice(double productPrice) {
		
		this.productPrice = productPrice;
	}
	
	public double getProductPrice() {
		
		return productPrice;
	}
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	public int getSelectedQuantity() {
		return selectedQuantity;
	}
	public void setSelectedQuantity(int selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}
	
	
	public int getProductSumPrice() {
		return productSumPrice;
	}
	public void setProductSumPrice(int productSumPrice) {
		this.productSumPrice = productSumPrice;
	}

	
	
}//closing brace of the class.
