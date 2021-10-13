<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AddFriend page</title>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="grid place-items-center h-screen bg-gray-200">
	<form action="AddFriendServlet" method="post" class="bg-white p-14 rounded-3xl">
		<h2 class="text-center text-4xl tracking-wide from-current">Add Friend </h2>
		<br><br>
		<%
    		session = request.getSession(false);
    		if(session.getAttribute("mobileMessage") != null) {
        		String mobileMessage = (String) session.getAttribute("mobileMessage");
        %>		
    	
    	<label class="text-red-600 font-serif text-md"><%out.print(mobileMessage);} %></label><br>
		<label class="text-gray-600 tracking-wider whitespace-normal text-sm">Enter Mobile Number</label><br>
		<input type="text" ple name="mobileNumber" class="border-yellow-600 border-b-2 container hover:border-green-600 border-b-2"><br><br><br>
		<input type="submit" value="ADD FRIEND" class="bg-purple-600 container p-1 rounded-3xl hover:bg-purple-800 text-white font-bold font-serif font-normal"/><br><br>
		<input type="button" value="CANCLE" onclick="window.location='home.jsp'" class="bg-purple-600 container p-1 rounded-3xl hover:bg-purple-800 text-white font-bold font-serif font-normal"/>
	</form>
</div>
</body>
</html>