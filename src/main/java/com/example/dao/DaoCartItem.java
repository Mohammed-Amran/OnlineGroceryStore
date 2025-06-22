package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.CartItem;


public class DaoCartItem {


	
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
	
	
	
		    //This method retrieves the numbers of items in the 'carItems' table.
		    public int getCartItemCount(int userId) {
		        
		    	String sql = "SELECT COUNT(*) FROM cartitems WHERE userId = ?";

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
		        
		    }//closing brace of the 'getCartItemCount()' method.	
	
	
	
//==================================================================================================================		
	
	
		    
		    //This method inserts items into 'cartItems' table
		    public boolean insertIntoCartItem(int userId, int selectedQuantity, int productSumPrice, String productName, int productPrice, int productId) {
		        
		        String sql = "INSERT INTO cartitems (userId, selectedQuantity, productSumPrice, productName, productPrice, productId) VALUES (?, ?, ?, ?, ?, ?)";

		        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
		            
		         
		            stmt.setInt(1, userId);
		            stmt.setInt(2, selectedQuantity);
		            stmt.setInt(3, productSumPrice);
		            stmt.setString(4, productName);
		            stmt.setInt(5, productPrice);
		            stmt.setInt(6, productId);
		           
		            int rowsInserted = stmt.executeUpdate();

		            return rowsInserted > 0; // return true if insertion was successful

		        }
		        catch (SQLException e) {
		        	
		            e.printStackTrace();
		            
		            return false;
		        }
		        
		        
		    }//closing brace of the 'insertIntoCartItem()' method.


		    
//=====================================================================================================-=============		    
		    
		    
		    
		  //This method retrieves items in the 'cartitems' table for a specific user using the userId.
		  public List<CartItem> getCartItemsByUserId(int userId) {
		  	  
		  	   
		  	   ArrayList<CartItem> itemsList = new ArrayList<>();
		  	  
		  	   
		  	   String sql = "SELECT cartItemId, userId, selectedQuantity, productSumPrice, productName, productPrice, productId FROM cartitems WHERE userId = ?";
		  	    
		  	   
		  	    try (Connection conn = getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
		  	         
		  	        stmt.setInt(1, userId);
		  	       
		  	        ResultSet rs = stmt.executeQuery();
		  	       
		  	        
		  	        while (rs.next()) {
		  	     
		  	        	
		  	        	//Instantiating an object from the 'cartItems' table.
		  	            CartItem item = new CartItem();
		  	            
		  	            
		  	            item.setCartItemId( rs.getInt("cartItemId") );
		  	            item.setUserId( rs.getInt("userId") );
		  	            item.setSelectedQuantity( rs.getInt("selectedQuantity") );
		  	            item.setProductSumPrice( rs.getInt("productSumPrice") );
		  	            item.setProductName( rs.getString("productName") );
                        item.setProductPrice( rs.getInt("productPrice") );
		  	            item.setProductId( rs.getInt( "productId" ) );
                        
		  	            itemsList.add(item);
		  	        }
		  	        
		  	        
		  	        
		  	    }
		  	    catch (SQLException e) {
					
		  	    	e.printStackTrace();
				}
		  	    
		  	    
		  	    return itemsList;
		  	    
		  	}//closing brace of the 'getCartItemByUserId' method.
		    
		    
		    
 //=====================================================================================================-============= 	    
		    
	
		  
		  
		//This method updates the selectedQuantity attribute in the 'cartItems' table - by incrementing it by 1.
		public boolean IncrementUpdateCartItemQuantity(int userId, int productId) {
			  
				String sql = "UPDATE cartitems SET selectedQuantity = selectedQuantity + 1 WHERE userId = ? AND productId = ?";

				
			    try (Connection conn = getConnection(); 
			         
			    		PreparedStatement stmt = conn.prepareStatement(sql)) {

			        stmt.setInt(1, userId);
			        stmt.setInt(2, productId);
			        

			        int rowsUpdated = stmt.executeUpdate();
			        return rowsUpdated > 0;

			    } 
			    catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			    
			    
			}//closing brace of the 'IncrementUpdateCartItemQuantity()' methods.

			
			
			
		//===================================================================================================================
			

			//This method updates the selectedQuantity attribute in the 'cartItems' table - by decrementing it by 1.
			public boolean decrementUpdateCartItemQuantity(int userId, int productId) {
			   
				// Firstly, check the current selectedQuantity
			    String checkQtySql = "SELECT selectedQuantity FROM cartitems WHERE userId = ? AND productId = ?";
			    
			    
			    try (Connection conn = getConnection();
			    		
			         PreparedStatement checkStmt = conn.prepareStatement(checkQtySql)) {

			        checkStmt.setInt(1, userId);
			        checkStmt.setInt(2, productId);
			        
			        ResultSet rs = checkStmt.executeQuery();

			        if (rs.next()) {
			        	
			            int currentQty = rs.getInt("selectedQuantity");
			            
			            //Checking if the current selectedQuantity is less than 2.
			            if (currentQty <= 1) {
			            	
			                // Remove the item if selectedQuantity is less than 2.
			                return removeCartItem(userId, productId);
			                
			            } 
			            else {
			            	
			                // Decrement quantity
			                String updateSql = "UPDATE cartitems SET selectedQuantity = selectedQuantity - 1 WHERE userId = ? AND productId = ?";
			                
			                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
			                	
			                    updateStmt.setInt(1, userId);
			                    updateStmt.setInt(2, productId);
			                    
			                    return updateStmt.executeUpdate() > 0;
			                    
			                }
			                
			            }
			            
			        } 
			        else {
			            
			        	return false; // Item not found
			        }
			    } 
			    catch (SQLException e) {
			       
			    	e.printStackTrace();
			       
			    	return false;
			    }
			    
			}

			
			

		//===================================================================================================================

			
			//This method removes the item in the cart using userId & itemId.
			public boolean removeCartItem(int userId, int productId) {
				
			    String sql = "DELETE FROM cartitems WHERE userId = ? AND productId = ?";
			    
			    try (Connection conn = getConnection();
			         PreparedStatement stmt = conn.prepareStatement(sql)) {
			        
			        stmt.setInt(1, userId);
			        stmt.setInt(2, productId);
			        
			        
			        int rowsDeleted = stmt.executeUpdate();
			        return rowsDeleted > 0;
			        
			    } 
			    catch (SQLException e) {
			       
			    	e.printStackTrace();
			       
			    	return false;
			    }
			    
			}//closing brace of the 'removeCartItem()' method
			
			
			
		//==================================================================================================================		  
		  
		    
		    
			//This method clears out all items in the cart that are added with reference to the user, using the userId.
			public void clearCart(int userId) {
				
			    String sql = "DELETE FROM cartitems WHERE userId = ?";

			    try (Connection conn = getConnection();
			    		
			         PreparedStatement stmt = conn.prepareStatement(sql)) {
			         
			        stmt.setInt(1, userId);
			        stmt.executeUpdate();

			    } 
			    catch (SQLException e) {
			    	
			        e.printStackTrace();
			    }
			    
			}//closing brace of the 'clearCart()' method.
			
		
		//===================================================================================================================   
		    
		
			
		//This method below retrieves the 'selectedQuantity' of a specific record
		//Based on the 'userId' & the 'productId':
			 public int retrieveSelectedQuantity(int userId, int productId) {
			    	
			    	
			    	String sql = "SELECT selectedQuantity FROM cartitems WHERE userId = ? AND productId = ?";
			    	
			    	try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			          stmt.setInt(1, userId);
			          stmt.setInt(2, productId);
			         

			          try (ResultSet rs = stmt.executeQuery()) {
			          	
			              if (rs.next()) {
			              	
			                  return rs.getInt("selectedQuantity");
			                  
			              }
			          }

			      } 
			    	catch (SQLException e) {
			    		
			          e.printStackTrace();
			      }

			      return -1; 
			    	
			    	
			    	
			    }
			
			
		
			 //===================================================================================================
			 
			 
			 
			 //This method below Updates 'productSumPrice' into 'cartitems' table based on 'userId' & 'productId':
			 public void updateProductSumPrice(int userId, int productId, int newProductSumPrice) {
				    
				    String sql = "UPDATE cartitems SET productSumPrice = ? WHERE userId = ? AND productId = ?";

				    try (
				          Connection conn = getConnection();
				          PreparedStatement stmt = conn.prepareStatement(sql)
				              
				        ) {

				        stmt.setInt(1, newProductSumPrice);
				        stmt.setInt(2, userId);
				        stmt.setInt(3, productId);

				        stmt.executeUpdate();

				    }       
				    catch (SQLException e) {
				        
				        e.printStackTrace();
				        
				    }
				    
				} //closing brace of the method.
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
		    
		    
		    
		    
}//closing brace of the 'DaoCartItem' class.

