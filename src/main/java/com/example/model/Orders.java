package com.example.model;


public class Orders {

	//Instance variables:
	private int orderId;
	private int userId;
	private String productName;
	private int productPrice;
	private int selectedQuantity;
	private int productSumPrice;
	private String selectedCity;
	private String address;
	
	
	
	//Constructor
	public Orders(){}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
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



	public int getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
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



	public String getSelectedCity() {
		return selectedCity;
	}



	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
	
	
	
	
	
}//closing brace of the class.

