package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Product;

public class DaoProduct {

	

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
	
	

//This method retrieves all Vegetables from the 'products' table:	    
  public List<Product> getVegetables() throws SQLException {

		 ArrayList<Product> itemsList = new ArrayList<>();

		       
		 String sql = "SELECT productId, productName, productDesc, productPrice, productCategory, productImgURL FROM products WHERE productCategory = ?";

			try (Connection conn = getConnection();

				 PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setString(1, "vegetables");

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {

						// Instantiating an object from the 'Product' class
						Product item = new Product();

						item.setProductId(rs.getInt("productId"));
						item.setProductName(rs.getString("productName"));
						item.setProductDesc(rs.getString("productDesc"));
						item.setProductPrice(rs.getInt("productPrice"));
						item.setProductCategory(rs.getString("productCategory"));
						item.setProductImgURL(rs.getString("productImgURL"));

						itemsList.add(item);
					}
				}

			}

	 return itemsList;

   } // closing brace of the getVegetables method

		    
		    
//==================================================================================================================		    
		    
	
  
  
//This method retrieves all Fruits from the 'products' table:	    
  public List<Product> getFruits() throws SQLException {

		 ArrayList<Product> itemsList = new ArrayList<>();

		       
		 String sql = "SELECT productId, productName, productDesc, productPrice, productCategory, productImgURL FROM products WHERE productCategory = ?";

			try (Connection conn = getConnection();

				 PreparedStatement stmt = conn.prepareStatement(sql)) {

				stmt.setString(1, "fruits");

				try (ResultSet rs = stmt.executeQuery()) {

					while (rs.next()) {

						// Instantiating an object from the 'Product' class
						Product item = new Product();

						item.setProductId(rs.getInt("productId"));
						item.setProductName(rs.getString("productName"));
						item.setProductDesc(rs.getString("productDesc"));
						item.setProductPrice(rs.getInt("productPrice"));
						item.setProductCategory(rs.getString("productCategory"));
						item.setProductImgURL(rs.getString("productImgURL"));

						itemsList.add(item);
					}
				}

			}

	 return itemsList;

   } // closing brace of the getVegetables method  
  
  
  
  
	
//==================================================================================================================	
	
	
  //This method below retrieves the 'productPrice' based on the 'productId'
  public int retrieveProductPrice(int productId) {
  	
  	
  	String sql = "SELECT productPrice FROM products WHERE productId = ?";
  	
  	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, productId);
       

        try (ResultSet rs = stmt.executeQuery()) {
        	
            if (rs.next()) {
            	
                return rs.getInt("productPrice");
                
            }
        }

    } 
  	catch (SQLException e) {
  		
        e.printStackTrace();
    }

    return -1; 
  	
  	
  	
  }
  
  
  
  
  
  
  
  
  
  
  
	
}//closing brace of the 'DaoProduct' class.

