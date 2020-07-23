<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	
	<title>Login</title>
	
	<style type="text/css">
		<%@include file="stylesheets/signin.css" %>
    </style>
    
</head>
	<body class="text-center">
		<form class="form-signin" action="login" method="post">
			<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
			<label for="inputEmail" class="sr-only">Email address</label>
			<input type="text" id="inputEmail" name="username" class="form-control" placeholder="Email address" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
	  		<div class="checkbox mb-3">
	    		<label>
	      			<input type="checkbox" value="remember-me"> Remember me
	    		</label>
	  		</div>
	  		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
	  		<h3></h3>
	  		<a href="AddUser.jsp" class="btn btn-secondary btn-sm active" role="button" aria-pressed="true">Create User</a>
		</form>
	</body>
</html>