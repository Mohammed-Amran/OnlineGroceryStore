<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>

<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Error Page</title>

<style>
  .center-box {
    width: 60%;
    margin: 100px auto;
    text-align: center;
    padding: 40px;
    background-color: #F2F3F1;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  }

  .center-box h1 {
    font-size: 32px;
    color: #4a403a;
  }

  .center-box p {
    font-size: 23px;
    color: red;
    margin-top: 10px;
    margin-bottom: 30px;
  }

  .center-btn {
    padding: 12px 24px;
    font-size: 16px;
    background-color: red;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }

  .center-btn:hover {
    background-color: grey;
  }
</style>

</head>

<body>


	<div class="center-box">

		<c:if test="${not empty requestScope.SessionError}">
			<p>
				<c:out value="${requestScope.SessionError}" />
			</p>
			<button class="center-btn"
				onclick="window.location.href='${pageContext.request.contextPath}/getBackToIndexFromError'">
				Get Back To Index Page</button>
		</c:if>

		<c:if test="${not empty requestScope.loginErrorMessage}">
			<p>
				<c:out value="${requestScope.loginErrorMessage}" />
			</p>
			<button class="center-btn"
				onclick="window.location.href='${pageContext.request.contextPath}/accessLoginPage'">
				Get Back To Login Page</button>
		</c:if>

		<c:if test="${not empty requestScope.registrationError}">
			<p>
				<c:out value="${requestScope.registrationError}" />
			</p>
			<button class="center-btn"
				onclick="window.location.href='${pageContext.request.contextPath}/accessRegisterPage'">
				Get Back To Register Page</button>
		</c:if>

	</div>




</body>

</html>