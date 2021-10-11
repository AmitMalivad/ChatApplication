<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.amit.util.HelperClass"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="grid place-items-center h-screen bg-gray-200">
<%
	String name = session.getAttribute("name") != null ? (String)session.getAttribute("name") : "";
	String profilePic = session.getAttribute("profilePic") != null ? (String)session.getAttribute("profilePic") : "";
	String defaultImage = HelperClass.getBaseUrl(request)+"\\images\\default.jpg";
	String img = profilePic.isBlank() ? defaultImage : profilePic;
%>
	<form action="UpdateProfileServlet" method="post" enctype="multipart/form-data" class="grid grid-cols-1 bg-white  divide-y divide-green-500 p-12 rounded-3xl">
		<div>
			<h1 class="text-center text-4xl tracking-wide from-current">Profile Update</h1><br>
			<label class="text-gray-600 tracking-wider whitespace-normal text-sm">User Name :</label>
			<input type="text" name="userName" value="<%out.println(name); %>" class="border-yellow-600 border-1 w-52 hover:border-green-600 border-b-2" /><br><br>
		</div>
		<div>
			<label class="text-gray-600 tracking-wider whitespace-normal text-sm">Profile Image :</label><br>
			<img alt="" src="<% out.println(img); %>" class=" h-40 w-40 p-5 rounded-full"> 
			<input type="file" name="profilePic" /><br><br>
		</div>
		<div>
			<br><input type="submit" value="SAVE" class="bg-purple-600 container p-1 rounded-3xl hover:bg-purple-800 text-white font-bold font-serif font-normal">
		</div>
	</form>
</div>
</body>
</html>