package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoUser {

	
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
	
	

	    //This method Inserts User into 'users' table
	    public boolean insertUser(String username, String email, String password) {
	    	
	    	
	        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

	        try ( 
	        	  Connection conn = getConnection(); 
	        	  PreparedStatement stmt = conn.prepareStatement(sql)
	        			  
	        	) {

	            stmt.setString(1, username);
	            stmt.setString(2, email);
	            stmt.setString(3, password);

	            int rowsInserted = stmt.executeUpdate();

	            return rowsInserted > 0; // true if at least one row inserted

	        }       
	        catch (SQLException e) {
	        	
	            e.printStackTrace();
	            return false;
	            
	        }
	        
	        
	        
	        
	    }//closing brace of the 'insertUser'.
		
		
	//==================================================================================================================    
	    
	
	  //Retrieving the id of user.
	    public int retrieveId(String email) {
	    	
	    	
	    	String sql = "SELECT userId FROM users WHERE email = ?";
	    	
	    	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	          stmt.setString(1, email);
	         

	          try (ResultSet rs = stmt.executeQuery()) {
	          	
	              if (rs.next()) {
	              	
	                  return rs.getInt("userId");
	                  
	              }
	          }

	      } 
	    	catch (SQLException e) {
	    		
	          e.printStackTrace();
	      }

	      return -1; // return Unknown id, if id not found or error occurs
	    	
	    	
	    	
	    }//closing brace of the 'retrieveId()' method.
	
	
	
	
	 //=============================================================================================================
	    
	  
	    //Checking if the user exists in the DB or not!? :
	    public boolean CheckUser(String email, String password) {
	    	
	        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	        
	        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	            
	            stmt.setString(1, email);
	            stmt.setString(2, password);
	            
	            try (ResultSet rs = stmt.executeQuery()) {
	            	
	                return rs.next();  // If there is a result, return true, else false
	            }
	            
	        } 
	        catch (SQLException e) {
	        	
	            e.printStackTrace();
	            return false;
	            
	        }
	        
	    }//closing brace of the 'CheckUser()' method.

	    
	    
	    
	  //==================================================================================================================    
	
	    
	    //Retrieving the username of user.
	    public String retrieveUserName(String email, String password) {
	    	
	    	
	    	String sql = "SELECT username FROM users WHERE email = ? AND password = ?";
	    	
	    	
	    	try (Connection conn = getConnection(); 
	    		 
	    		 PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, email);
	            stmt.setString(2, password);

	            try (ResultSet rs = stmt.executeQuery()) {
	            	
	                if (rs.next()) {
	                	
	                    return rs.getString("username");
	                    
	                }
	                
	            }

	        } 
	    	catch (SQLException e) {
	    		
	            e.printStackTrace();
	        }
	    	

	        return "Unknown Name"; // return Unknown UserName if not found or error occurs
	    	
	    	
	 
	    }//closing brace of the 'retrieveUserName()' method 
	
	
	    
//==========================================================================================================	    
	    
	    
	    
	    
}//closing brace of the 'DaoUser' class.

