<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Same Favorites Page</title>
</head>
<body>
	<h1><a href="UserHomePage.jsp">Back to Home</a></h1>
	<div align="center">
		<form action="viewfavoritelist" method="get">
			<table border="1" cellpadding="5">
	            <caption><h2>Same Favorite Lists</h2></caption>
	            <tr>
	                <th>User One</th>
	                <th>User Two</th>
	            </tr>
	            <c:forEach var="user" items="${users}">
	                <tr>
	                    <td>
	                    	<c:out value="${user.userOne}" />
	                    	<button name="username" type="submit" value=<c:out value="${user.userOne}" />>View Favorite List</button>
	                    </td>
	                    <td>
	                    	<c:out value="${user.userTwo}" />
	                    	<button name="username" type="submit" value=<c:out value="${user.userTwo}" />>View Favorite List</button>
	                   	</td>
	                </tr>
	            </c:forEach>
	        </table>
        </form>
	</div>
</body>
</html>