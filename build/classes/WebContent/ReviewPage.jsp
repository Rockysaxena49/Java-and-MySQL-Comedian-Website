<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Page</title>
</head>
<body>
	<h1>Review Page</h1>
<h2><a href="VideoPage.jsp">Back to User Video Page</a></h2>

	<form action="writeReview" method="POST">
		<div align="center">
			<label>Write Review</label>
			<input type="text" name="review" required>
		</div>
		
		<br>
		<div align="center">
			<label>Rating</label>
			<select name = "rating">
				<option value="P">Poor</option>
				<option value="F">Fair</option>
				<option value="G">Good</option>
				<option value="E">Excellent</option>
			</select>
		</div>
		
		<br>
		<div align="center">
		<input type="submit" name="submit" value="Submit">
		</div>
		
	</form>
</body>
</html>