<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="UserHomePage.jsp">Hope Page</a>
	<form action="addcomedian" method="post">
		<div align="center">
			<h2>Insert a Comedian into Database</h2>
			<table border="1" cellpadding="5">        
            <tr><th>Comedian ID: </th>
                <td>
                    <input type="text" name="comid" size="6" required/>
                </td>
            </tr>
            <tr><th>First Name: </th>
                <td><input type="text" name="firstName" size="45" required/></td>
            </tr>
            <tr><th>Last Name: </th>
                <td><input type="text" name="lastName" size="45" required/></td>
            </tr>
            <tr><th>Birthday: </th>
                <td><input type="text" name="birthday" size="45" placeholder="yyyy-mm-dd" required/></td>
            </tr>
            <tr><th>Birth Place: </th>
                <td><input type="text" name="birthPlace" size="45" required/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save" /></td>
            </tr>
       	 </table>
  	 </form>
</body>
</html>