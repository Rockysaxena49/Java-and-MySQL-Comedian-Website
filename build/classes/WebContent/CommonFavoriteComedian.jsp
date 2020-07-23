<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Common Favorite Comedian</title>
</head>
<body>
	<body>
	<h1><a href="UserHomePage.jsp">Back to Home</a></h1>
	<div align="center">
		<form action="commoncomedian" method="get">
			<table border="1" cellpadding="5">
	            <caption><h2>Select two different users to compare</h2></caption>
	            <tr>
	                <th>User One</th>
	                <th>User Two</th>
	            </tr>
	            <tr>
	            	<td>
	            		<label for="users">Choose User One: </label>
							<select name="username1" id="username1">
							<c:forEach var="result" items="${users}">
								<option value="<c:out value="${result.username}" />"><c:out value="${result.username}" /></option>
							</c:forEach>
							</select>
	            	</td>
	            	<td>
	            		<label for="users">Choose User Two: </label>
							<select name="username2" id="username2">
							<c:forEach var="result" items="${users}">
								<option value="<c:out value="${result.username}" />"><c:out value="${result.username}" /></option>
							</c:forEach>
							</select>
					</td>
	            </tr>
	            <tr><td colspan="2" align="center"><input type="submit" value="Get Common List" /></td></tr>
	        </table>
        </form>
	</div>
	<div align="center">
		<h2>Common Favorite Comedians</h2>
		<c:forEach var="result" items="${commonList}">
			<h3><c:out value="${result}" /></h3>
		</c:forEach>
	</div>
</body>
</body>
</html>