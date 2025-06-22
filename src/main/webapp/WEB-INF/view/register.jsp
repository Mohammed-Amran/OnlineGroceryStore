
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
    
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Register Page</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerStyle.css">

<%-- Link to google fonts --%>	
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Chewy&display=swap" rel="stylesheet">
	


<style type="text/css">

body {
	 background:  linear-gradient(rgba(255, 255, 255, 0.5)), 
                  url('${pageContext.request.contextPath}/images/loginBackImg.png') no-repeat center center fixed;
        
     background-size: cover; 
     
}

</style>

</head>

<body>



 <div id="main">

        <div id="heading">
        
            <h1 style="font-family: 'Chewy', system-ui; color: #344C3D;">Sign Up</h1>
            <h4>Create your account</h4>
            
        </div>


        <div class="card">

            <form name="registerForm" method="post" action="registerUser">


                <label for="username" style="font-size: 20px; color: #344C3D;"> Username: </label>
                <input type="text" id="username" name="username" value="${requestScope.username}"  required>

                <label for="email" style="font-size: 20px; color: #344C3D;"> Email: </label>
                <input type="email" id="email" name="email" value="${requestScope.email}"  required>
                <c:if test="${not empty emailError}">
                
                   <div style="color:red;">${emailError}</div> <br>
                   
                </c:if>

                <label for="password" style="font-size: 20px; color: #344C3D;"> Password: </label>
                <input type="password" id="password" name="password" value="${requestScope.password}" required>

                


                <div class="button-container">
                
                      <button type="submit" name="register" value="signup" class="btn" style="font-size: 22px;">Sign Up</button>
                    
                            
                     <button type="button" name="registerButton" value="registerCancel" class="cancel" style="color: red;"  onclick="window.location.href='${pageContext.request.contextPath}/getBackToLoginFromRegister'"> Cancel </button>
              
                </div>


            </form>

        </div>

    </div>



</body>
</html>
