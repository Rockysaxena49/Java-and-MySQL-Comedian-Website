<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Who's Cool</title>
</head>
<body>
	<h1><a href="UserHomePage.jsp">Back to Home</a></h1>
	<div align="center">
		<table border="1" cellpadding="5">
            <caption><h2>List of Comedians who have all Excellent Reviews</h2></caption>
            <tr>
                <th>comid</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Birthday</th>
                <th>Birth Place</th>
                
            </tr>
            <c:forEach var="excellent" items="${excellentReview}">
                <tr>
                    <td><c:out value="${excellent.comid}" /></td>
                    <td><a href= "excellentVideoPage?paramvalue1=${excellent.comid}" ><c:out value="${excellent.firstName}" /></a></td>
                    <td><c:out value="${excellent.lastName}" /></td>
                    <td><c:out value="${excellent.birthday}" /></td>
                    <td><c:out value="${excellent.birthPlace}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>