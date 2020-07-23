<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Popular Tag</title>
</head>
<body>
	<h1><a href="UserHomePage.jsp">Back to Home</a></h1>
	<div align="center">
		<table border="1" cellpadding="5">
            <h2>Tags used by every user</h2>
            <tr>
                <th>Popular Tags</th>
                
            </tr>
            <c:forEach var="popTags" items="${tags}">
                <tr>
                    <td><c:out value="${popTags}" /></td>
                  
                </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>