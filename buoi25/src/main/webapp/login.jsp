<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="login" method="post">
		<label>Username:</label>
		<input type="text" name="username">
		<br>
		<label>Password:</label>
		<input type="text" name="password">
		<br> 
		<button>Submit</button>
	</form>
	
	<c:if test="${ error == true}">
		<h3>Sai username hoặc mật khẩu</h3>
	</c:if>
</body>
</html>