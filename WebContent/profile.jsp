<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.amit.util.HelperClass"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String name = session.getAttribute("name") != null ? (String)session.getAttribute("name") : "";
	String profilePic = session.getAttribute("profilePic") != null ? (String)session.getAttribute("profilePic") : "";
	String defaultImage = HelperClass.getBaseUrl(request)+"\\images\\default.jpg";
	String img = profilePic.isBlank() ? defaultImage : profilePic;
%>
	<form action="UpdateProfileServlet" method="post" enctype="multipart/form-data">
	<input type="text" name="userName" value="<%out.println(name); %>">
	<img alt="" src="<% out.println(img); %>"> 
	<input type="file" name="profilePic" />
	<input type="submit" value="SAVE">
	</form>
</body>
</html>