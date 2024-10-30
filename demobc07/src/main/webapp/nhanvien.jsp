<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees</title>
</head>
<body>
	<c:forEach items="${employeesList}" var="item">
		<h3>${item.name}</h3>
		<h3>${item.salary}</h3>

	</c:forEach>
</body>
</html>