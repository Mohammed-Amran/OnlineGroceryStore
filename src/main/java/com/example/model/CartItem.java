package com.example.model;

public class CartItem {

	//Instance variables:
	private int cartItemId;
	private int userId;
	private int selectedQuantity;
	private int productSumPrice; // = (producctPrice * selectedQuantity)
	private String productName;
	private int productPrice;
	private int productId;
	
	


	//Constructor
	public CartItem() { }


	

	public int getProductId() {
		return productId;
	}




	public void setProductId(int productId) {
		this.productId = productId;
	}




	public int getCartItemId() {
		return cartItemId;
	}



	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
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
	
	
	
	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public int getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	

	
}//closing brace of the class.
