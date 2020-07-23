<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
	<title><c:out value="${videoData.title}" /></title>

	<style type="text/css">
		<%@include file="stylesheets/videopage.css" %>
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
	<div class="video-block">
		<iframe width="660" height="415" src="https://www.youtube.com/embed/${cutUrl}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
	</div>
	<div class="video-info">
		<div class="title-text">
			<c:out value="${videoData.title}" />
		</div>
		<div class="post-user">
			<c:out value="${videoData.postUser}" />
		</div>
		<div class="post-date">
			<c:out value="${videoData.postDate}" />
		</div>
		<hr>
		<div class="post-user">
			<p><c:out value="${videoData.videoDescription}" /></p>
		</div>
		<hr>
	</div>
	<!-- If user posted review already or posted the video hide the write review section -->
	<div align="left" style="display:${hasReview ? 'none' : 'block'}; width: 650px">
		<div class="review-block">
			<!-- Action to write a review -->
			<form action="writeReview" method="POST">
				<div>
					<textarea id="review" name="review" rows="5" cols="50" placeholder="Write a Review" required></textarea>
					<label>Rating</label>
					<select name = "rating">
						<option value="P">Poor</option>
						<option value="F">Fair</option>
						<option value="G">Good</option>
						<option value="E">Excellent</option>
					</select>
				</div>
				<div>
					<!-- need to save the url link because redirecting gets rid of it -->
					<button name="url" class="btn btn-primary btn-sm active" type="submit" value=<c:out value="${fullUrl}" />>Submit Review</button>
				</div>
			</form>
		</div>
	</div>
	<br>
	<div style="display:${isFavorite ? 'none' : 'block'}">
		<form action="videoaddfavorite" method="post">
			<div style="margin-left: 40px">
				<button name="url" class="btn btn-primary btn-sm active" type="submit" value=<c:out value="${fullUrl}" />>Add Comedian to Favorites</button>
			</div>
		</form>
		<div style="margin-left: 40px; width: 660px">
			<hr>
		</div>
	</div>
	<!-- Used to display all the comments of the youtube video -->
	<div class="comments">
		<c:forEach var="result" items="${allReviews}">
			<div class="comment-user">
				<c:out value="${result.author}" />
			</div>
			<div class="comment-remark">
				<c:out value="${result.remark}" />
			</div>
			<br>
		</c:forEach>
	</div>
	</body>
</html>