<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
<title>Upload Video</title>

	<style type="text/css">
		<%@include file="stylesheets/addvideopage.css" %>
    </style>

</head>
<body>
	<div class="topnavbar">
			<div class="container2">
				<div class="login-button">
					<a href="UserHomePage.jsp" class="btn btn-light" role="button" aria-pressed="true">Home Page</a>
				</div>
				<div class="page-title">
					Upload a New Video to the Database
				</div>
			</div>
		</div>
		<!-- Call upload function -->
	<form action="upload" method="POST">
		<div align="center" style="padding-top: 20px">
			<label>URL</label>
			<input type="text" name="URL" placeholder="YouTube link" required>
		</div>
		
		<br>
		<div align="center">
			<label>Title</label>
			<input type="text" name="Title" required>
		</div>
		
		<br>
		<div align="center">
			<label>Description</label>
			<input type="text" name="Description" required>
		</div>
		
		<br>
		<div align="center">
			<label>Tags</label>
			<input type="text" name="Tags" placeholder="Tag1, Tag2, ..." required>
		</div>
		
		<br>
		<!--  Extract all comedians from variable using loop-->
		<div align="center">
			<label for="comedians">Choose a Comedian: </label>
			<select name="comid" id="comid">
			<c:forEach var="result" items="${comedians}"> <!-- Store comedians variable in result variable -->
				<option value="<c:out value="${result.comid}" />"><c:out value="${result.firstName} ${result.lastName }" /></option>
			</c:forEach>
			</select>
          	<button class="btn btn-primary btn-sm" type="submit">Add</button>
		</div>
	</form>
</body>
</html>