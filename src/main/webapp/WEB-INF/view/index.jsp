<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&family=Parisienne&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/indexStyle.css">

<title>Viewer Only Page</title>

 <style>
    
    .carousel {
      width: 100%;
      max-width: 1200px; /* Adjust as needed */
      margin: 20px auto;
    }
    
    .carousel-inner {
      border-radius: 15px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
      overflow: hidden;
    }
    
    .carousel-item img {
      width: 100%;
      height: 500px; /* Adjust height as needed */
      object-fit: cover;
    }
    
    .hero-content {
  text-align: center;
  padding: 20px;
}




.menu-container {
  position: relative;
  padding: 60px 20px;
  
}




.menu-title, 
.menu-items {
  position: relative;
  z-index: 1;
}

.login-btn{

transition: transform 0.2s ease;
}

.login-btn:hover {
	transform: scale(1.05);
}


.category-btn{
transition: transform 0.2s ease;
}

.category-btn:hover {
	transform: scale(1.08);
	 box-shadow: 0 4px 12px rgba(255, 255, 255, 0.6);
}

.menu-title{

transition: transform 0.2s ease;
}

.menu-title:hover{

   transform: scale(1.08);
}
    
    
.appbar-brand h1{

transition: transform 0.2s ease;
}    

.appbar-brand h1:hover {
	
  transform: scale(1.08);
}   


.hero-content h2 {

transition: transform 0.2s ease;
}    

.hero-content h2:hover {
	
  transform: scale(1.08);
} 
 
    
</style>


</head>

<body>


<!-- APP bar -->
<nav class="appbar">

    <div class="appbar-brand">
    
        <h1>Grocery Store</h1>
        
    </div>
    
    
    <div class="appbar-actions">
    
    
     <form method="get" action="accessDetailsPage">  
     
        <button class="btn login-btn" type="submit" >       
             Product Details          
        </button>
        
     </form>
       
     
     <form method="get" action="accessLoginPage">  
     
        <button class="btn login-btn" type="submit" >       
             Login          
        </button>
        
     </form>
       
        
    </div>
    
</nav>


<!-- Hero Section with Gallery -->
<section class="hero">

  <div class="hero-content">
  
        <h2>Freshly Grocery Items</h2>
        <p>Explore our handpicked groceries, thoughtfully sourced for freshness and flavor</p>
        
    </div>

  <div style="display: flex; justify-content: center;">

    <!-- Carousel inserted here -->
    <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
  
  
  
  <div class="carousel-inner">
  
    <div class="carousel-item active" data-bs-interval="5000">
    
      <img src="${pageContext.request.contextPath}/images/marketStall.jpg" class="d-block" alt="Bread 1">
      
    </div>
    
    <div class="carousel-item" data-bs-interval="3000">
    
      <img src="${pageContext.request.contextPath}/images/groceryImg1.jpg" class="d-block" alt="Croissant">
      
    </div>
    
    <div class="carousel-item">
    
      <img src="${pageContext.request.contextPath}/images/groceryImg2.jpg" class="d-block" alt="Pastry">
      
    </div>
    
    <div class="carousel-item">
    
      <img src="${pageContext.request.contextPath}/images/groceryImg3.jpg" class="d-block" alt="Pastry">
      
    </div>
    
    <div class="carousel-item">
    
      <img src="${pageContext.request.contextPath}/images/groceryImg4.jpg" class="d-block" alt="Pastry">
      
    </div>
    
   
    
    
    
  </div>
  
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
  
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
    
  </button>
  
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
  
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
    
  </button>
  
</div>

</div>
    
</section>





<!-- Main Content - Menu Section -->
<main class="menu-container" style="background-color: #8EA58C">

 <div >
 
    <h2 class="menu-title">  Our Menu </h2>
    
     
    <!-- Category Buttons -->
     <div style="display: flex; justify-content: center; gap: 30px; margin-bottom: 8px;">
               
           <form method="get" action="indexPagecategoryController">    
            
                <input type="hidden" name="category" value="vegetable">
            
                <button type="submit" class="category-btn" style="background-color: #F2F3F1; color: #344C3D; border: 2px solid #8EA58C; 
                        padding: 8px 20px; border-radius: 5px; border-color: #344C3D; font-size: 18px; cursor: pointer;">
                        
                    Vegetables
                                    
                </button>
                
           </form>     
            
           <form method="get" action="indexPagecategoryController">  
                  
                 <input type="hidden" name="category" value="fruit"> 
                  
                <button type="submit" class="category-btn" style="background-color: #F2F3F1; color: #344C3D; border: 2px solid #8EA58C; 
                        padding: 8px 20px; border-radius: 5px; border-color: #344C3D; font-size: 18px; cursor: pointer;">
                        
                    Fruits
                    
                </button>
                
           </form>
                
      </div>
    
    
    <div class="menu-items" style="background-color: #F2F3F1">
       
       
    <div class="menu-grid" style="margin-top: 40px;">
       
    <c:choose>
    
       <c:when test="${requestScope.showCategory == 'vegetable'}">
        
          <c:forEach var="veg" items="${sessionScope.VegetablesProductsList}">
      
      
                  <div class="card">
       
                       <div class="card-content">
           
                            <img src="${pageContext.request.contextPath}${veg.productImgURL}" class="item-img" />
            
                            <h1>${veg.productName}</h1>
               
                       </div>

                  </div>
     
     
           </c:forEach>   
      
      </c:when>
      
      
      
      <c:when test="${requestScope.showCategory == 'fruit'}">
      
      
            <c:forEach var="fruit" items="${sessionScope.FruitsProductsList}">
      
      
                  <div class="card">
       
                       <div class="card-content">
           
                            <img src="${pageContext.request.contextPath}${fruit.productImgURL}" class="item-img" />
            
                            <h1>${fruit.productName}</h1>
               
                       </div>

                  </div>
     
     
           </c:forEach> 
            
            
              
        
      </c:when>
      
      
      
   </c:choose>
       
       </div>
       
       
    </div>
    
    </div>
    
</main>

<!-- Footer -->
<footer class="footer">
    <p>&copy; 2025 Grocery Store. All rights reserved.</p>
</footer>

<!-- Font Awesome for icons -->
<script src="https://kit.fontawesome.com/a076d05399.js"></script>


<!-- Bootstrap JS + Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>