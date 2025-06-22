<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	




<!DOCTYPE html>

<html>

<head>

<title>Grocery Store</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<%-- Link to the CSS file for this customer view page --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/homeStyle.css">


<%-- Link to the CSS file for this userInfo modal view page --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/userInfoModalStyle.css">




<%-- Link to google fonts --%>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
	
<%-- Link to google fonts --%>	
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">
	
	
<link rel = "shortcut icon" href="favicon.ico" type="image/x-icon">
 <link rel="preconnect" href="https://fonts.gstatic.com">
 <link href="https://fonts.googleapis.com/css2?family=Parisienne&display=swap" rel="stylesheet">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 
 
 
 <script src="https://kit.fontawesome.com/815d5311a8.js" crossorigin="anonymous"></script>
 
 <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
 
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 <script src="mycart.js"></script>
 <script src="myscript.js"></script>	
	

<style type="text/css">

body {
	 background:  linear-gradient(rgba(255, 255, 255, 0.5)), 
                  url('${pageContext.request.contextPath}/images/backgroundImg2.jpg') no-repeat center center fixed;
        
     background-size: cover; 
         
}


.close-btn{

transition: transform 0.2s ease;
}

.close-btn:hover{

transform: scale(1.05);
}

.logout-btn{

transition: transform 0.2s ease;
}

.logout-btn:hover{

transform: scale(1.05);
}

.detailsBtn{

color: #344C3D;
transition: transform 0.2s ease;
background-color: #8EA58C;
}

.detailsBtn:hover{

transform: scale(1.05);
color: #F2F3F1;
background-color: #8EA58C;
}

.navtop a{

color: #344C3D;
transition: transform 0.2s ease;
background-color: #8EA58C;
}

.navtop a:hover{

transform: scale(1.05);
color: #F2F3F1;
background-color: #8EA58C;
}

/* Modal Styles (to match cards) */
.modal-content {
    border: 2px solid #8EA58C !important;
    border-radius: 8px !important;
    background-color: #F2F3F1 !important;
}

.modal-header {
    background-color: #8EA58C !important;
    color: #344C3D !important;
}

.modal-img-container img {
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 5px;
    border: 1px solid #BFCFBB;
}

.price-dropdown {
    width: 100%;
    padding: 10px;
    border: 1px solid #8EA58C;
    border-radius: 4px;
    background-color: #F2F3F1;
    color: #344C3D;
}


.category-btn {
  background-color: #F2F3F1;
  color: #344C3D;
  border: 2px solid #8EA58C;
  padding: 8px 20px;
  border-radius: 5px;
  font-size: 18px;
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease, background-color 0.2s ease;
}

.category-btn:hover {
  transform: translateY(-4px) scale(1.03); /* Slight upward pop-out */
  background-color: #8EA58C;
  box-shadow: 0 0 12px #8EA58C;
  color: white; /* Optional: make text white when hovered */
}





</style>	
	

</head>


<!--=========================-- Body of the WebPage --========================-->

<body>




	<!--  N A V I G A T I O N   B A R  -->
	<div class="topnav" id="myTopnav">


		<div class="navtop" id="mynavTop">

           
           
 
           <a href="" style="float: left;" data-toggle="modal" data-target="#userModal"> <i class="fas fa-user"></i> </a>  
			
            <a href="${pageContext.request.contextPath}/accessCartPage" style="float: right;"> <i class="fas fa-shopping-cart"></i> <span class="cart-items"> ( <c:if test="${empty sessionScope.cartCounter }"> 0 </c:if> ${sessionScope.cartCounter} ) </span> </a> 

            <a href="${pageContext.request.contextPath}/accessCheckoutPage" style="float: right;" > <i class="fas fa-box-open"></i> <span class="inbox-items"> ( <c:if test="${empty sessionScope.inboxCounter }"> 0 </c:if> ${sessionScope.inboxCounter} ) </span> </a>

		</div>


	</div>


<!-- =========================================================================================================================== -->
<!-- ==============================================MODAL'S(POP-UP WINDOWS)====================================================== -->


<!-- ========- USER INFO MODAL -======= -->

	<!-- Modal (pop-up window) for the User Info -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
    
    <div class="modal-dialog" role="document">
        
        <div class="modal-content" style="border: 2px solid #8EA58C; border-radius: 8px; background-color: #F2F3F1;">
           
           
            <!-- Modal Header -->
            <div class="modal-header" style="background-color: #8EA58C; border-radius: 6px 6px 0 0; padding: 15px 20px;">
            
                <h5 class="modal-title" id="userModalLabel" style="color: #344C3D; font-size: 24px; font-weight: 600; font-family: 'Chewy', system-ui;">
                    
                    <i class="fas fa-user-circle"></i> User Information
               
                </h5>
           
            </div>

           
            <!-- Modal Body -->
            <div class="modal-body" style="padding: 20px;">
               
                <c:choose>
                    
                    <c:when test="${not empty sessionScope.username || not empty sessionScope.email}">
                       
                        <!-- User Details Container -->
                        <div class="user-details" style="margin-bottom: 20px;">
                        
                            <p style="margin-bottom: 15px;">
                            
                                <strong style="color: #344C3D; display: inline-block; width: 80px; font-family: 'Chewy', system-ui;">UserName:</strong>
                                <span style="color: #344C3D; font-weight: 600; font-size: 20px;"><c:out value="${sessionScope.username}" /></span>
                                
                            </p>
                            
                            <p style="margin-bottom: 15px;">
                            
                                <strong style="color: #344C3D; display: inline-block; width: 80px; font-family: 'Chewy', system-ui;">Email:</strong>
                                <span style="color: #344C3D; font-weight: 600;"><c:out value="${sessionScope.email}" /></span>
                                
                            </p>
                            
                            
                            
                        </div>
                        
                    </c:when>
                    
                    
                    <c:otherwise>
                    
                        <p style="text-align: center; color: #cb997e; font-size: 16px;">
                        
                            <i class="fas fa-exclamation-circle"></i> User details not available.
                            
                        </p>
                        
                    </c:otherwise>
                    
                </c:choose>
                
            </div>


            <!-- Modal Footer -->
            <div class="modal-footer" style="border-top: 1px solid #8EA58C; padding: 15px 20px;">
            
                <button type="button" class="close-btn" data-dismiss="modal" style="background-color: #BFCFBB; color: #344C3D; border: none; padding: 8px 16px; border-radius: 4px; font-family: 'Chewy', system-ui;">                  
                    Close                  
                </button>
                
                <form name="logOutForm" method="get" action="${pageContext.request.contextPath}/Logout" style="display: inline;">
                
                    <button type="submit" class="logout-btn" style="background-color: #BFCFBB; color: red; border: none; padding: 8px 16px; border-radius: 4px; font-family: 'Chewy', system-ui;">
                         Logout
                    </button>
                    
                </form>
                
            </div> <!-- closing tag of the modal footer -->
            
        </div>
        
    </div>
    
</div>



<!-- ################################################################################################################################ -->



	<!--=========================--  M A I N   C O N T E N T  --====================-->

	<div id="heading">

		<h1 id="heading-title" style="margin-top: 40px; font-family: 'Chewy', system-ui; text-shadow: none; font-size: 55px;">Grocery Store</h1>

	</div>


	<!--  I T E M   C A R D S  -->

	<div id="main" style="margin-top: 40px;">


    <!-- Category buttons -->
	<div style="width: 100%; display: flex; justify-content: center; margin-bottom: 8px;">
 
         <form method="get" action="HomecategoryController" style="margin: 0 15px;">
    
              <input type="hidden" name="category" value="vegetable">
    
              <button type="submit" class="category-btn" style="background-color: #F2F3F1; color: #344C3D; border: 2px solid #8EA58C; 
                                                                padding: 8px 20px; border-radius: 5px; border-color: #344C3D; font-size: 18px; cursor: pointer;">
      
                      Vegetables
    
              </button>
  
         </form>


        <form method="get" action="HomecategoryController" style="margin: 0 15px;">
   
            <input type="hidden" name="category" value="fruit">
    
            <button type="submit" class="category-btn" style="background-color: #F2F3F1; color: #344C3D; border: 2px solid #8EA58C; 
                                                              padding: 8px 20px; border-radius: 5px; border-color: #344C3D; font-size: 18px; cursor: pointer;">
     
                     Fruits
    
            </button>
  
       </form>


    </div>


      
      

  <div class="menu-grid" style=" background-color: #F2F3F1; display: grid; grid-template-columns: repeat(2, 1fr); gap: 60px; max-width: 1800px; margin: 0 auto; padding: 40px; ">
				

               <c:choose>
						
					
						<c:when test="${requestScope.showCategory == 'vegetable'}">


							<c:forEach var="veg" items="${sessionScope.VegetablesProductsList}">

								<!-- Product Card -->
								<div class="card" style="background-color: white; border-radius: 15px; overflow: hidden; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); display: flex; flex-direction: column; height: 95%;">

									<div class="card-content" style="flex: 1; padding-bottom: 20px;">
										<img src="${pageContext.request.contextPath}${veg.productImgURL}" class="item-img" style="width: 100%; height: 300px; object-fit: cover; border-radius: 15px 15px 0 0;" />
											 
										<h1 style="text-align: center; color: #344C3D; font-size: 38px; font-weight: bold; margin: 25px 0; padding: 0 20px; min-height: 1em;">
											
											${veg.productName}
											
										</h1>
									
									</div>

									<div class="card-action" style="padding: 0 20px 25px 20px; text-align: center; margin-top: auto;">
										
										<button class="btn btn-primary" id="detailsBtn" style="background-color: #8EA58C; color: white; border: none; padding: 15px 40px; border-radius: 8px; font-size: 18px; font-weight: 500; cursor: pointer; margin-top: 10px;" data-toggle="modal" data-target="#modal-${veg.productId}">
											
											Select
											
									    </button>
									    
									</div>
									
								</div>

								
								<div class="modal fade" id="modal-${veg.productId}" tabindex="-1" role="dialog" aria-hidden="true">
									
									<div class="modal-dialog" role="document">
										
										<div class="modal-content" style="border: 2px solid #8EA58C; border-radius: 8px; background-color: #F2F3F1;">

											<!-- Modal Header -->
											<div class="modal-header" style="background-color: #8EA58C; border-radius: 6px 6px 0 0; padding: 15px 20px;">
												
												<h5 class="modal-title" style="color: #344C3D; font-size: 24px; font-weight: 600; font-family: 'Chewy', system-ui;">
													
													${veg.productName}
													
											    </h5>
											
											</div> <!-- closing tag of the modal-header -->

											<form method="post" action="${pageContext.request.contextPath}/addItemIntoCart">	
												
												<div class="modal-body" style="padding: 20px;">

													<!-- Image -->
													<div style="margin-bottom: 20px; text-align: center;">
														
														<img src="${pageContext.request.contextPath}${veg.productImgURL}"
															 style="width: 100%; height: 200px; object-fit: cover; border-radius: 5px; border: 1px solid #BFCFBB;">
													</div>

									              

											        <p style="font-size: 16px; color: #344C3D;">${veg.productDesc}</p>

                                                    

											        <select id="itemDropdown" class="price-dropdown" name="selectedQuantity">

												               <option value="1"> 1 kg ~  <c:out value="${veg.productPrice}" /> iqd </option>
												               <option value="2"> 2 kg's ~ <c:out value="${veg.productPrice * 2}" /> iqd </option>
												               <option value="4"> 4 kg's ~ <c:out value="${veg.productPrice * 4}" /> iqd </option>
												               

											        </select>

										              

									            </div>

												
												<div class="modal-footer" style="border-top: 1px solid #8EA58C; padding: 15px 20px;">
													
													
													     <input type="hidden" name="productId" value="${veg.productId}">
													     <input type="hidden" name="productName" value="${veg.productName}">													     
													     <input type="hidden" name="productPrice" value="${veg.productPrice}">
													     <input type="hidden" name="productCategory" value="${veg.productCategory}">
													     
													
													     <button type="submit" class="btn btn-secondary" 
														         style="background-color: #BFCFBB; color: green; border: none; padding: 8px 16px; border-radius: 4px; font-family: 'Chewy', system-ui;">
														
														      Add To Cart
														
												         </button>
													
												
													     <button type="button" class="btn btn-secondary" data-dismiss="modal"
														         style="background-color: #BFCFBB; color: red; border: none; padding: 8px 16px; border-radius: 4px; font-family: 'Chewy', system-ui;">
														
														    Close
														
												        </button>
												    
												</div> <!-- closing tag of the modal footer -->
												
											</form>
											
										</div> <!-- closing tag of the modal-content -->
										
									</div>
									
								</div>


							</c:forEach>



						</c:when>


                        
						<c:when test="${requestScope.showCategory == 'fruit'}">
						
							<c:forEach var="fruit" items="${sessionScope.FruitsProductsList}">
							
							
								<!-- Product Card -->
								<div class="card" style="background-color: white; border-radius: 15px; overflow: hidden; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); display: flex; flex-direction: column; height: 95%;">

									<div class="card-content" style="flex: 1; padding-bottom: 20px;">
										<img src="${pageContext.request.contextPath}${fruit.productImgURL}" class="item-img" style="width: 100%; height: 300px; object-fit: cover; border-radius: 15px 15px 0 0;" />
											 
										<h1 style="text-align: center; color: #344C3D; font-size: 38px; font-weight: bold; margin: 25px 0; padding: 0 20px; min-height: 1em;">
											
											${fruit.productName}
											
										</h1>
									
									</div>

									<div class="card-action" style="padding: 0 20px 25px 20px; text-align: center; margin-top: auto;">
										
										<button class="btn btn-primary" id="detailsBtn" style="background-color: #8EA58C; color: white; border: none; padding: 15px 40px; border-radius: 8px; font-size: 18px; font-weight: 500; cursor: pointer; margin-top: 10px;" data-toggle="modal" data-target="#modal-${fruit.productId}">
											
											Select
											
									    </button>
									    
									</div>
									
								</div>

								
								<div class="modal fade" id="modal-${fruit.productId}" tabindex="-1" role="dialog" aria-hidden="true">
									
									<div class="modal-dialog" role="document">
										
										<div class="modal-content" style="border: 2px solid #8EA58C; border-radius: 8px; background-color: #F2F3F1;">

											<!-- Modal Header -->
											<div class="modal-header" style="background-color: #8EA58C; border-radius: 6px 6px 0 0; padding: 15px 20px;">
												
												<h5 class="modal-title" style="color: #344C3D; font-size: 24px; font-weight: 600; font-family: 'Chewy', system-ui;">
													
													${fruit.productName}
													
											    </h5>
											
											</div>

											
											<form method="post" action="${pageContext.request.contextPath}/addItemIntoCart">	
												
												<div class="modal-body" style="padding: 20px;">

													<!-- Image -->
													<div style="margin-bottom: 20px; text-align: center;">
														
														<img src="${pageContext.request.contextPath}${fruit.productImgURL}"
															 style="width: 100%; height: 200px; object-fit: cover; border-radius: 5px; border: 1px solid #BFCFBB;">
													</div>

									              

											        <p style="font-size: 16px; color: #344C3D;">${fruit.productDesc}</p>

                                                    

											        <select id="itemDropdown" class="price-dropdown" name="selectedQuantity">

												               <option value="1"> 1 kg ~  <c:out value="${fruit.productPrice}" /> iqd </option>
												               <option value="2"> 2 kg's ~ <c:out value="${fruit.productPrice * 2}" /> iqd </option>
												               <option value="4"> 4 kg's ~ <c:out value="${fruit.productPrice * 4}" /> iqd </option>
												               

											        </select>

										              

									            </div>

												
												<div class="modal-footer" style="border-top: 1px solid #8EA58C; padding: 15px 20px;">
													
													
													     <input type="hidden" name="productId" value="${fruit.productId}">
													     <input type="hidden" name="productName" value="${fruit.productName}">
													     <input type="hidden" name="productPrice" value="${fruit.productPrice}">
													     <input type="hidden" name="productCategory" value="${fruit.productCategory}">
													
													     <button type="submit" class="btn btn-secondary" 
														         style="background-color: #BFCFBB; color: green; border: none; padding: 8px 16px; border-radius: 4px; font-family: 'Chewy', system-ui;">
														
														      Add To Cart
														
												         </button>
													
												
													     <button type="button" class="btn btn-secondary" data-dismiss="modal"
														         style="background-color: #BFCFBB; color: red; border: none; padding: 8px 16px; border-radius: 4px; font-family: 'Chewy', system-ui;">
														
														    Close
														
												        </button>
												    
												</div> <!-- closing tag of the modal footer -->
												
											</form>
											
										</div>
										
									</div>
									
								</div>

								
								
							</c:forEach>
							
							
						</c:when>
						
						
					</c:choose>


       </div>


    </div> <!-- End of Main Div -->



<script>

//This 'openModal()' method! allows us to select the items & and be able to Add them into the cart.

 let selectedItem = {}; // Object to hold the details of the selected item.

 // Function to open the modal and populate details
 function openModal(id, title, description) {
	 
	 selectedItem.id = id;
	    selectedItem.title = title;
	    
	    // Get the specific modal for this item
	    var modal = $('#itemModal' + id);
	    
	    // Update the modal content
	    modal.find('.modal-title').text(title);
	    modal.find('#itemDescription').text(description);
	    
	    // Open the correct modal
	    modal.modal('show');
    
    
 }//closing brace of the 'openModal()' method.




</script>

	
		


<!--********************************************************************************************************-->






	<!-- Footer -->
	<div id="footer" style="margin-top: 40px;">

		<!-- Phone Numbers -->
		<div class="footer__contact">
		
			<i>+964 750 141 8006</i> 
			
		</div>

		<!-- Social Media Icons -->
		<div class="footer__social">
		
			<ul class="horizontal-list text-center social-icons">
			
				<!-- Instagram Icon -->
				<li> <a href="#"> <i class="fab fa-instagram"></i> </a> </li>
				
				<!-- YouTube Icon -->
				<li> <a href="#"> <i class="fab fa-youtube"></i> </a> </li>
				
				<!-- Facebook Icon -->
				<li> <a href="#"> <i class="fab fa-facebook"></i> </a> </li>
				
			</ul>
			
		</div>

		<!-- Email -->
		<div class="footer__mail">
		
			<i>Grocery2025@gmail.com</i>
			
		</div>

	</div>




</body>

</html>
