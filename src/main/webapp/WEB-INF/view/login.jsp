<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
	
	
	
<!DOCTYPE html>

<html>


<head>

<meta charset="UTF-8">
<title>Login Page</title>

<%-- Link to google fonts --%>	
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginErrorModalStyle.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<style type="text/css">




#heading{

background-color: #BFCFBB;
border: 1px solid #344C3D;
}

#heading h1{

 font-size: 50px;
 color: #344C3D;
}


.card{

background-color: #BFCFBB;
border: 1px solid #344C3D;

}

 input[type="email"]:hover{
        
        border: 1px solid #344C3D;
        }
        
        input[type="password"]:hover{
        
        border: 1px solid #344C3D;
        }
        
        
 button.btn {
            background-color: #BFCFBB;
            color: #344C3D;
            
            transition: transform 0.2s ease;
            
        }

        button.btn:hover {
            
            color: #344C3D;
            background-color: white;
            transform: scale(1.05);
        }        


button.btn{
 font-size: 25px;
}

.signup-link{
  
 font-family:'Chewy', system-ui;
 font-size: 16px;
}

</style>

</head>



<body style="background:  linear-gradient(rgba(255, 255, 255, 0.5)), url('${pageContext.request.contextPath}/images/backgroundImg2.jpg') no-repeat center center fixed;
             background-size: cover; ">

 


  
<!-- If the user logged out! the 'Logout-alert' pop-up window will be shown  --> 
<c:if test="${not empty requestScope.logOutMessage}">
  
  <script type="text/javascript">
 
   $(document).ready(function () { $('#logoutModal').modal('show'); });
 
   </script>
  
</c:if>
 
   
  

	<div id="main">

		<div id="heading">
		
			<h1 style="font-family: 'Chewy', system-ui; color: #344C3D; text-shadow: none;">Grocery Store</h1>
			
			<h4 style="font-family: 'Chewy', system-ui; font-size:18px; font-weight: bold;" >Please log in to continue</h4>
			
		</div>




		<div class="card">

			<form name="loginForm" action="Login" method="post">


				<label for="email" style="font-family: 'Chewy', system-ui; font-size: 20px; color: #344C3D; margin-bottom: 0px;"> <b>Email</b> </label> 
				<input type="email" id="email" name="email" placeholder="testuser@gmail.com" style="background-color:#F2F3F1; font-family: 'Chewy', system-ui;" value="testuser@gmail.com" required> 
				
				<label for="password" style="font-family: 'Chewy', system-ui; font-size: 20px; color: #344C3D; margin-bottom: 0px; margin-top: 5px;"> <b>Password</b> </label>
				<input type="password" id="password" name="password" placeholder="test12345" style="background-color:#F2F3F1; font-family: 'Chewy', system-ui;" value="test12345"  required>

                <div style="display: flex; gap: 22px; justify-content: center; margin-top: 15px;"> 

				   <button type="submit" class="btn" style="font-family: 'Chewy', system-ui; font-weight: bold; margin-top: 7px; padding-top: 8px; background-color: #F2F3F1;"> Login </button>

                   <button type="button" class="btn" style="font-family: 'Chewy', system-ui; font-weight: bold; margin-top: 7px; padding-top: 8px; background-color: #F2F3F1; color: Red;" onclick="window.location.href='${pageContext.request.contextPath}/getBackToIndexPageFromLogin'"> Cancel </button>

               </div>

			</form>



			<div class="signup-link">
		
               <p>Don't have an account? <a href="${pageContext.request.contextPath}/accessRegisterPage">Sign Up</a></p>

			</div>

		</div>


	</div>




   <!-- Logout Message Modal -->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalLabel" aria-hidden="true">
		
		<div class="modal-dialog" role="document">

			<div class="modal-content">

				<div class="modal-header">
				
					<h5 class="modal-title" id="logoutModalLabel">Logged Out</h5>
					
				</div>

				<div class="modal-body">
					
					<p> <c:out value="${requestScope.logOutMessage}" /> </p>
				
				</div>

				<div class="modal-footer">
					
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					
				</div>

			</div>

		</div>
		
	</div> <!-- Closing tag of the 'Logout Message' modal -->



</body>


</html>