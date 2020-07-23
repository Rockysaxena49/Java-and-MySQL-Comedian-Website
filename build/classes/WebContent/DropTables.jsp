<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>INITIALIZING THE DATABASE WILL DELETE ALL OF THE CURRENT DATA</h1>
		<h1>ARE YOU SURE YOU WANT TO DO THIS?</h1>
		<form action="drop" method="post">
			<input type="submit" value="YES">
		</form>
		<button onclick="document.location = 'UserHomePage.jsp'">NO</button>
	</div>
</body>
</html>