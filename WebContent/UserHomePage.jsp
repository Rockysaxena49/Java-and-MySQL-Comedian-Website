<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
		
	<title>Home Page</title>
	
	<style type="text/css">
		<%@include file="stylesheets/userhomepage.css" %>
    </style>

</head>
	<body>
		<div style="display:${isRoot ? 'none' : 'block'}">
			<% if(session == null){ response.sendRedirect("LoginForm.jsp");}%>
			<div class="topnavbar">
				<div class="container2">
					<div class="login-button">
						<a href="LoginForm.jsp" class="btn btn-light" role="button" aria-pressed="true">Sign Out</a>
					</div>
					<form action="search" method="get">
						<div class="search-bar">
							<div class="input-group flex-nowrap">
								<input type="text" style="width: 500px" class="form-control" name="search" placeholder="Search by comedian name or tags" aria-label="Username" aria-describedby="addon-wrapping" required>
							</div>
						</div>
						<div class="search-button">
							<button class="btn btn-secondary btn-sm active" type="submit">Search</button>
						</div>
					</form>
					<!-- > This code below is to upload a video -->
					<form action="toaddvideo" method="get">
						<div class="add-video-button">
							<button class="btn btn-primary btn-sm active" type="submit">Upload Video</button>
						</div>
					</form>
				</div>
			</div>
			<div class="user-header">
				<div style="font-size: 20px; font-weight: bold;">
					Welcome <c:out value="${user.firstName}" /> <c:out value="${user.lastName}" />!
				</div>
			</div>
				<div class="user-info">
					<table>
						<tr>
							<td>Username: </td>
							<td><c:out value="${user.username}" /></td>
						</tr>
						<tr>
							<td>Password: </td>
							<td><c:out value="${user.password}" /></td>
						</tr>
						<tr>
							<td>Age: </td>
							<td><c:out value="${isRoot ? '' : user.age}" /></td>
						</tr>
					</table>
				</div>
				<div class="user-header">
					<hr>
				</div>
			<div style="padding-top: 20px; padding-left: 10px">
				<form action="favoritelist" method="get">
					<button class="btn btn-primary btn-lg active" type="submit">Display Favorite Comedians</button>
				</form>
			</div>
		</div>
		<!-- ------------------------USER^-------------------------------------------------------------------------------------------------- -->
		<div style="display:${isRoot ? 'block' : 'none'}" align="center">
			<div class="topnavbar">
					<div class="container2">
						<div class="login-button">
							<a href="LoginForm.jsp" class="btn btn-light" role="button" aria-pressed="true">Sign Out</a>
						</div>
					</div>
				</div>
			<h2>Admin Functions</h2>
			<table>
				<tr>
					<td>
						<button onclick="document.location = 'DropTables.jsp'">Initialize Database</button>
					</td>
					<td>
						<form action="list" method="get">
							<button type="submit">View Registered Users</button>
						</form>
					</td>
					<td><button onclick="document.location = 'AddComedian.jsp'">Insert A Comedian</button></td>
				</tr>
				<tr>
					<td>
						<form action="gettopcomedian" method="get">
							<button type="submit">Top Comedian</button>
						</form>
					</td>
					<td>
						<form action="commoncomedian" method="get">
							<button type="submit">Common Favorite Comedian</button>
						</form>
					</td>
					<td>
						<form action="postivereviewers" method="get">
							<button type="submit">Display Positive Reviewers</button>
						</form>
					</td>
					<td>
						<form action="poorvideos" method="get">
							<button type="submit">Display Poor Videos</button>
						</form>
					</td>
					<td>
						<form action="samefavorites" method="get">
							<button type="submit">Display Same Favorite Lists</button>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form action="postedToday" method="get">
							<button type="submit"> Who's New</button>
						</form>	
					</td>
					<td>
						<form action="topReview" method="get">
							<button type="submit"> Who's Hot</button>
						</form>
					</td>
					<td>
						<form action="topTags" method="get">
							<button type="submit"> Popular Tags</button>
						</form>
					</td>
					<td>
						<form action="excellentReviews" method="get">
							<button type="submit"> Who's Cool</button>
						</form>
					</td>
					<td>
						<form action="mostUploads" method="get">
							<button type="submit"> Most Productive User</button>
						</form>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>