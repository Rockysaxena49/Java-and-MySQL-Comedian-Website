<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
	<title>Favorite Comedians</title>
	
	<style type="text/css">
		<%@include file="stylesheets/searchresults.css" %>
    </style>
    
</head>
	<body>
		<div class="topbar">
			<div class="container">
				<div class="home-button">
					<a href="UserHomePage.jsp" class="btn btn-light" role="button" aria-pressed="true">Home Page</a>
				</div>
			</div>
		</div>
		<div class="resulttable">
			<h1 align="center">Favorite Comedians</h1>
	        <table class="table table-striped">
	        	<thead class="thead-dark">
	        		<tr>
	        			<th scope="col">First Name</th>
	        			<th scope="col">Last Name</th>
	        			<th scope="col">Birthday</th>
	        			<th scope="col">Birthplace</th>
	        			<th scope="col">Action</th>
	        		</tr>
	        	</thead>
	        	<tbody>
	        		<c:forEach var="result" items="${favoriteList}">
		                <tr>
		                    <td><c:out value="${result.firstName}" /></td>
		                    <td><c:out value="${result.lastName}" /></td>
		                    <td><c:out value="${result.birthday}" /></td>
		                    <td><c:out value="${result.birthPlace}" /></td>
		                    <td><a href="deletefavorite?id=<c:out value='${result.comid}' />" class='btn btn-danger btn-sm active' role='button' aria-pressed='true'>Delete</a></td>
		                </tr>
	            	</c:forEach>
	            	<form action="addfavorite" method="post">
		            	<td colspan="4">
							<label for="comedians">Choose a Comedian: </label>
							<select name="comid" id="comid">
								<c:forEach var="result" items="${comedians}">
									<option value="<c:out value="${result.comid}" />"><c:out value="${result.firstName} ${result.lastName }" /></option>
								</c:forEach>
							</select>
		            	</td>
		            	<td><button class="btn btn-primary btn-sm" type="submit">Add</button></td>
	            	</form>
	        	</tbody>
	        </table>
		</div>
	</body>
</html>