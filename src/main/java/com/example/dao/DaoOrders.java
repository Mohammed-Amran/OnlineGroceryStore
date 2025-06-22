package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Orders;

public class DaoOrders {

	
	//==================================================================================================================	

	//JDBC URL for MySQL connection
    private final String jdbcURL = "jdbc:mysql://localhost:3306/grocerydb?useSSL=false&serverTimezone=UTC";
    private final String jdbcUsername = "root"; // Our MySQL username
    private final String jdbcPassword = "1234"; // Our MySQL password 

    
    // Method to get connection to MySQL Database
    private Connection getConnection() throws SQLException {
      
    	
    	try {
    		
    		//Step-1: Loading the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            
        } 
    	catch (ClassNotFoundException e) {
    		
            e.printStackTrace();
        }
    	
    
    	
    	
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        
             
    }//closing brace of the 'getConnection()' method.

    
//==================================================================================================================		
	
	
	
  //This method retrieves the numbers of items in the 'orders' table.
    public int getOrderedItemsCount(int userId) {
        
    	String sql = "SELECT COUNT(*) FROM orders WHERE userId = ?";

        try (Connection conn = getConnection();
             
        	 PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	
                return rs.getInt(1);
            }

        } 
        catch (SQLException e) {
        	
            e.printStackTrace();
        }

        return 0; // Return 0 if error occurs
        
    }//closing brace of the 'getOrderedItemsCount()' method.
    
    

//==================================================================================================================	  
    
    //This method inserts items into 'orders' table   
    public boolean insertIntoOrders(int userId, String productName, int productPrice, int selectedQuantity, int productSumPrice, String selectedCity, String Address) {
        
        String sql = "INSERT INTO orders (userId, productName, productPrice, selectedQuantity, productSumPrice, selectedCity, Address) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
         
            stmt.setInt(1, userId);
            stmt.setString(2, productName);
            stmt.setInt(3, productPrice);
            stmt.setInt(4, selectedQuantity);
            stmt.setInt(5, productSumPrice);
            stmt.setString(6, selectedCity);
            stmt.setString(7, Address);
           
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0; // return true if insertion was successful

        }
        catch (SQLException e) {
        	
            e.printStackTrace();
            
            return false;
        }
        
        
    }//closing brace of the 'insertIntoOrders()' method.


    
//=====================================================================================================-=============	
    
    
    
      //This method retrieves items in the 'orders' table for a specific user using the userId.
	  public List<Orders> getOrderedItemsByUserId(int userId) {
	  	  
	  	   
	  	   ArrayList<Orders> itemsList = new ArrayList<>();
	  	  
	  	   
	  	   String sql = "SELECT orderId, userId, productName, productPrice, selectedQuantity, productSumPrice, selectedCity, Address FROM orders WHERE userId = ?";
	  	    
	  	   
	  	    try (Connection conn = getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
	  	         
	  	        stmt.setInt(1, userId);
	  	       
	  	        ResultSet rs = stmt.executeQuery();
	  	       
	  	        
	  	        while (rs.next()) {
	  	     	  	        	
	  	        	//Instantiating an object from the 'cartItems' table.
	  	            Orders order = new Orders();
	  	            
	  	            order.setOrderId( rs.getInt("orderId") );
	  	            order.setUserId( rs.getInt("userId") );
	  	            order.setProductName( rs.getString("productName") );
	  	            order.setProductPrice( rs.getInt("productPrice") );
	  	            order.setSelectedQuantity( rs.getInt("selectedQuantity") );
	  	            order.setProductSumPrice( rs.getInt("productSumPrice") );
	  	            order.setSelectedCity( rs.getString("selectedCity") );
	  	            order.setAddress( rs.getString("Address") );

	  	            
	  	            itemsList.add(order);
	  	        }
	  	        
	  	        
	  	        
	  	    }
	  	    catch (SQLException e) {
				
	  	    	e.printStackTrace();
			}
	  	    
	  	    
	  	    return itemsList;
	  	    
	  	}//closing brace of the 'getCartItemByUserId' method.
	    
	    
	    
//=====================================================================================================-=============
    
    
    
    
	
	
}//closing brace of the class.

