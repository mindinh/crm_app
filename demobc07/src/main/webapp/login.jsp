<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Login</h2>
	<% String path = request.getContextPath(); %>
	
	<form action="<%= path %>/login" method="post">
		<label>User name: </label>
		<input type="text" name="username">
		
		<label>Password: </label>
		<input type="text" name="password">
		
		<button type="submit">Login</button>
	</form>
</body>
</html>