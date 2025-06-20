package com.example.model;

public class User {

	//Instance variables:
	private int userId;
	private String username;
	private String email;
	private String password;
	
	
	//Constructor:
	public User(int userId, String username, String email, String password) {
		
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	
	//=========== GETTERS & SETTERS =====================
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	
	
}//closing brace of the class.
