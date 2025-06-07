<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>

<title>Cart Page</title>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<%-- Link to the CSS file for this cart page --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/homeStyle.css">

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

<style type="text/css">

body {
	background: linear-gradient(rgba(255, 255, 255, 0.5)), 
                url('${pageContext.request.contextPath}/images/backgroundImg2.jpg') no-repeat center center fixed;
    background-size: cover; 
}

/* App Bar Styles */
.app-bar {
    background-color: #8EA58C;
    padding: 15px 20px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    position: sticky;
    top: 0;
    z-index: 1000;
}

.back-btn {
    background: none;
    border: none;
    color: #344C3D;
    font-size: 24px;
    cursor: pointer;
    transition: transform 0.2s ease;
    padding: 5px;
}

.back-btn:hover {
    transform: scale(1.1);
    color: #F2F3F1;
}

/* Hero Section Styles */
.hero-section {
    background-color: #F2F3F1;
    border: 2px solid #8EA58C;
    border-radius: 15px;
    padding: 30px;
    margin: 30px auto;
    max-width: 800px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.hero-title {
    color: #344C3D;
    font-family: 'Chewy', system-ui;
    font-size: 28px;
    text-align: center;
    margin-bottom: 25px;
}

.address-row {
    display: flex;
    gap: 20px;
    align-items: flex-start;
}

.city-select {
    flex: 1;
    padding: 12px;
    border: 2px solid #8EA58C;
    border-radius: 8px;
    background-color: white;
    color: #344C3D;
    font-size: 16px;
    font-family: 'Poppins', sans-serif;
}

.address-textarea {
    flex: 2;
    padding: 12px;
    border: 2px solid #8EA58C;
    border-radius: 8px;
    background-color: white;
    color: #344C3D;
    font-size: 16px;
    font-family: 'Poppins', sans-serif;
    resize: vertical;
    min-height: 80px;
}

.city-select:focus,
.address-textarea:focus {
    outline: none;
    border-color: #344C3D;
    box-shadow: 0 0 8px rgba(142, 165, 140, 0.3);
}

/* Cart Items Styles */
.cart-container {
    max-width: 1000px;
    margin: 30px auto;
    padding: 0 20px;
}

.cart-title {
    color: #344C3D;
    font-family: 'Chewy', system-ui;
    font-size: 36px;
    text-align: center;
    margin-bottom: 30px;
    text-shadow: none;
}

.cart-item {
    background-color: white;
    border: 2px solid #8EA58C;
    border-radius: 12px;
    padding: 20px;
    margin-bottom: 15px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.cart-item:hover {
    transform: translateY(-2px);
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
}

.item-details {
    display: flex;
    gap: 30px;
    align-items: center;
    flex: 1;
}

.item-name {
    color: #344C3D;
    font-size: 20px;
    font-weight: 600;
    font-family: 'Poppins', sans-serif;
    min-width: 150px;
}

.item-price {
    color: #8EA58C;
    font-size: 18px;
    font-weight: 500;
    min-width: 100px;
}

.item-quantity {
    color: #344C3D;
    font-size: 16px;
    font-weight: 500;
    min-width: 80px;
}

.item-total {
    color: #344C3D;
    font-size: 20px;
    font-weight: bold;
    min-width: 120px;
    text-align: right;
}

.empty-cart {
    text-align: center;
    color: #8EA58C;
    font-size: 24px;
    font-family: 'Chewy', system-ui;
    padding: 60px 20px;
    background-color: white;
    border: 2px solid #8EA58C;
    border-radius: 12px;
    margin: 30px auto;
    max-width: 500px;
}

.checkout-section {
    background-color: white;
    border: 2px solid #8EA58C;
    border-radius: 12px;
    padding: 25px;
    margin: 30px auto;
    max-width: 500px;
    text-align: center;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.total-amount {
    color: #344C3D;
    font-size: 28px;
    font-weight: bold;
    font-family: 'Chewy', system-ui;
    margin-bottom: 20px;
}

.checkout-btn {
    background-color: #8EA58C;
    color: white;
    border: none;
    padding: 15px 40px;
    border-radius: 8px;
    font-size: 18px;
    font-weight: 600;
    font-family: 'Poppins', sans-serif;
    cursor: pointer;
    transition: transform 0.2s ease, background-color 0.2s ease;
}

.checkout-btn:hover {
    transform: scale(1.05);
    background-color: #344C3D;
}

/* Responsive Design */
@media (max-width: 768px) {
    .address-row {
        flex-direction: column;
        gap: 15px;
    }
    
    .item-details {
        flex-direction: column;
        gap: 10px;
        align-items: flex-start;
    }
    
    .cart-item {
        flex-direction: column;
        align-items: stretch;
    }
    
    .item-total {
        text-align: center;
        margin-top: 15px;
        padding-top: 15px;
        border-top: 1px solid #BFCFBB;
    }
}


.cart-header {
    
    font-weight: bold;
    background-color: #f5f5f5;
    border-bottom: 2px solid #ddd;
}

.header-details {
    display: flex;
    gap: 20px;
}

.header-name, .header-price, .header-quantity, .header-total {
    font-weight: bold;
    color: #333;
}

</style>

</head>

<body>


    <!-- App Bar -->
    <div class="app-bar">
    
     <form method="get" action="${pageContext.request.contextPath}/getBackToHomeFromCheckout">
    
        <button type="submit" class="back-btn" >
        
            <i class="fas fa-arrow-left"></i>
            
        </button>
       
       </form>
        
    </div> <!-- closing tag of the app-bar -->




    

    


		<!-- Cart Items Section -->
		<div class="cart-container">
		
			<h1 class="cart-title">Ordered Items</h1>

			<c:choose>
			
				<c:when test="${not empty requestScope.orderedItems}">

				<!-- Cart Header -->
				<div style="border-radius: 10px; margin-bottom: 9px; padding: 15px 20px; /* Top/Bottom: 15px, Left/Right: 20px */ background: #f8f9fa;" class="cart-header">
					
					<div class="item-details" style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
						
						<div class="item-name" style="flex: 2; font-size: 20px; text-align: left; padding-left: 10px; font-family: 'Chewy', system-ui; color: #5c6b5b;"> Product Name </div>
						
						<div class="item-price" style="flex: 1; font-size: 20px; text-align: center; padding: 0 10px;"> Unit Price </div>
						
						<div class="item-quantity" style="flex: 1; font-size: 20px; text-align: center; padding: 0 10px;"> Quantity </div>
						
						<div class="item-city" style="flex: 1; font-size: 20px; text-align: center; padding: 0 10px; color: #8EA58C;"> City </div>
						
						<div class="item-address" style="flex: 2; font-size: 20px; text-align: center; padding: 0 10px; color: #5c6b5b;"> Address </div>
						
						<div class="item-total" style="flex: 1; font-size: 20px; text-align: right; padding-right: 10px; color: #8EA58C;"> Total Price </div>
					
					</div>
				
				</div>

				<c:set var="totalAmount" value="0" />

				<c:forEach var="orderedItem" items="${requestScope.orderedItems}">
					
					<div class="cart-item">

						<div class="item-details">

							<div class="item-name" style="font-family: 'Chewy', system-ui; color: #5c6b5b;">
							
								<c:out value="${orderedItem.productName}" />
								
							</div>

							<div class="item-price">
							
								<c:out value="${orderedItem.productPrice}" />
								IQD
								
							</div>

							<div class="item-quantity" style="padding-left: 39px;">
							
								<c:out value="${orderedItem.selectedQuantity}" />
								
							</div>

							<!-- NEW: Show City and Address -->
							<div style="font-size: 20px; padding-left: 65px; color: #8EA58C;" class="item-city">
							
								
								<c:out value="${selectedCity}" />
								
							</div>
							
							<div style="font-size: 20px; padding-left: 90px; color: #5c6b5b;" class="item-address">
							
								
								<c:out value="${Address}" />
								
							</div>

						</div>

						<div class="item-total" style="color: #8EA58C;">
						
							<c:out value="${orderedItem.productSumPrice}" />
							IQD
							
						</div>

					</div>

					<c:set var="totalAmount" value="${totalAmount + orderedItem.productSumPrice}" />
				
				</c:forEach>




				<!-- Checkout Section -->
					<div class="checkout-section">
					
						<div class="total-amount">
							Total: &nbsp
							
						<span style="color: #8EA58C;"> <c:out value="${totalAmount}" /> IQD </span>	
							
							
						
						</div>
						
						
						
					
					</div>

				</c:when>
				
				
				<c:otherwise>
				
					<div class="empty-cart">
					
						<i class="fas fa-box-open" style="font-size: 48px; margin-bottom: 20px; display: block;"></i>
						
						You haven't ordered any item!
					
					</div>
				
				</c:otherwise>
			
			</c:choose>
		
		</div>


	
   

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