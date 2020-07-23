<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Top User Video Page</title>
</head>
<body>
<h1><a href="mostUploads">Go Back</a></h1>

<caption><h2>All Videos</h2></caption>

	      
    <c:forEach var="allVideos" items="${videos}">
    
       	<div>
       		<iframe width="660" height="415" src="https://www.youtube.com/embed/${allVideos.url}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
       	</div>
       	<div class="title-text">
           <h3>Title: <c:out value="${allVideos.title}" /></h3>
        </div>
        <div>
           <h4>Uploaded by: <c:out value="${allVideos.postUser}" /></h4>
        </div>
        <div>
           <h4>Date Posted: <c:out value="${allVideos.postDate}" /></h4>
        </div>
        <div>
           <h4>Video Description: <c:out value="${allVideos.videoDescription}" /></h4>
        </div>
        <br>
        <br>  
      </c:forEach>
        
</body>
</html>