<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.amit.dto.*" %>	
 <%@ page import="java.util.List" %>	
    <%
    if(session == null || session.getAttribute("isValid") == null || session.getAttribute("isValid") != "true") {
    	String indexPage = "./index.jsp";
    	response.sendRedirect(indexPage);
    }
    List<Friend> friendList = (List<Friend>)session.getAttribute("friendList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Insert title here</title>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="bg-green-600 h-52 sm:h-42 md:h-32 lg:h-24">
	<div class="grid grid-cols-1 sm:flex sm:float-left ">
		<img alt="" src="<%
		String profilePic = (String)session.getAttribute("profilePic");
		out.print(profilePic);%>" class="bg-left-top h-32 w-32 md:h-28 md:w-28 lg:h-24 lg:w-24 p-2 rounded-full float-left">
		<div class="grid grid-cols-1 sm:grid-cols-1">
			<%String name = (String)session.getAttribute("name");%>
			<label class="font-serif text-2xl ml-2 mt-6 sm:ml-5 "><%out.println(name); %></label>
			<label class="font-serif text-md ml-2  sm:ml-5 ">Online</label>
		</div>
	</div>
	<div class="flex float-right mt-6 mr-6 sm:hidden">
		<button class="icon fa fa-bars" onclick="myFunction()"></button>
	</div>
	<div class="hidden sm:flex sm:space-x-7 sm:float-right sm:mt-6 sm:mr-6">
		<button><i class="icon fa fa-comment"> Chat</i></button>
		<button class="" onclick="window.location='profile.jsp'"><i class="icon fa fa-user"> Profile</i></button>
		<!-- <input type="button" value="ProfileUpdate" onclick="window.location='profile.jsp'"/> -->
		<button><i class="icon fa fa-envelope-open"> Message</i></button>
		<button onclick="window.location='addfriend.jsp'"><i class="icon fa fa-address-book"></i> Friend</button>
	</div>
</div>

<div class="bg-blue-200 h-screen w-auto sm:w-auto md:w-1/2 lg:w-1/3">
	 <% if(friendList != null){ 
	 	for(int i = 0; i < friendList.size(); i++) { %>
	 <div class="divide-y divide-green-500">
		<!-- <td><% out.println(friendList.get(i).getName());  out.println(friendList.get(i).getImg()); %></td> -->
		<img alt="" src="<%out.println(friendList.get(i).getImg()); %>" class="bg-left-top h-20 w-20 p-2 rounded-full float-left">
		<div class="grid grid-cols-1">
			<label class="font-serif text-2xl ml-2 mt-6 sm:ml-5 "><%out.println(friendList.get(i).getName()); %></label>
			<label class="font-serif text-md ml-2  sm:ml-5 ">Online</label>
		</div>
	</div> <% }} %>
</div>

</body>
</html>