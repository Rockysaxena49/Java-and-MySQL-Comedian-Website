<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Most Productive User</title>
</head>
<body>
	<h1><a href="UserHomePage.jsp">Back to Home</a></h1>
	<div align="center">
		<table border="1" cellpadding="5">
            <caption><h2>List of users who posted the most </h2></caption>
            <tr>
                <th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Age</th>
                
            </tr>
            <c:forEach var="top" items="${topPosts}">
                <tr>
                    <td><a href= "topPostVideoPage?paramvalue1=${top.username}" ><c:out value="${top.username}" /></a></td>
                    <td><c:out value="${top.firstName}" /></td>
                    <td><c:out value="${top.lastName}" /></td>
                    <td><c:out value="${top.age}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>