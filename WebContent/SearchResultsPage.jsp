<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
	<title>Search Results</title>
	
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
				<form action="search">
					<div class="search-bar">
						<div class="input-group flex-nowrap">
							<input type="text" style="width: 500px" class="form-control" name="search" placeholder="Search by comedian name or tags" aria-label="Username" aria-describedby="addon-wrapping" required>
						</div>
					</div>
					<div class="search-button">
						<button class="btn btn-secondary btn-sm active" type="submit">Search</button>
					</div>
				</form>
			</div>
		</div>
		<form action="tovideopage" method="get">
			<div class="resulttable">
			<h1 align="center">Search Results for "${userInput}"</h1>
		        <table class="table table-striped">
		        	<thead class="thead-dark">
		        		<tr>
		        			<th scope="col">Video Title</th>
		        			<th scope="col">Post User</th>
		        			<th scope="col">Video URL</th>
		        			<th scope="col">Action</th>
		        		</tr>
		        	</thead>
		        	<tbody>
		        		<c:forEach var="result" items="${searchResults}">
			                <tr>
			                    <td><c:out value="${result.title}" /></td>
			                    <td><c:out value="${result.postUser}" /></td>
			                    <td><a href=<c:out value="${result.url}" />><c:out value="${result.url}" /></a></td>
			                    <td><button name="url" class="btn btn-secondary btn-sm active" type="submit" value=<c:out value="${result.url}" />>Watch Video</button></td>
			                </tr>
		            </c:forEach>
		        	</tbody>
		        </table>
			</div>
		</form>
	</body>
</html>