<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><a href="UserHomePage.jsp">Back to Home</a></h1>
	<div align="center">
		<table border="1" cellpadding="5">
            <caption><h2>Videos with Poor Reviews</h2></caption>
            <tr>
                <th>Video Title</th>
            </tr>
            <c:forEach var="videos" items="${videos}">
                <tr>
                    <td><c:out value="${videos}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
</body>
</html>