<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Who's New</title>
</head>
<body>
	<h1><a href="UserHomePage.jsp">Back to Home</a></h1>
	<div align="center">
		<table border="1" cellpadding="5">
            <caption><h2>List of New Comedians</h2></caption>
            <tr>
                <th>comid</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Birthday</th>
                <th>Birth Place</th>
                
            </tr>
            <c:forEach var="comid" items="${comedians}">
                <tr>
                    <td><c:out value="${comid.comid}" /></td>
                    <td><c:out value="${comid.firstName}" /></td>
                    <td><c:out value="${comid.lastName}" /></td>
                    <td><c:out value="${comid.birthday}" /></td>
                    <td><c:out value="${comid.birthPlace}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>