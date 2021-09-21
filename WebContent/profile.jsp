<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.amit.util.HelperClass"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="UpdateProfileServlet" method="post" enctype="multipart/form-data">
	<input type="text" name="userName" >
	<%	String defaultImage = HelperClass.getBaseUrl(request)+"\\images\\default.jpg";	%>
	<img alt="" src="<% out.print(defaultImage); %>"> 
	<input type="file" name="profilePic" />
	<input type="submit" value="SAVE">
</form>
</body>
</html>