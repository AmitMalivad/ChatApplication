<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.amit.dto.*" %>	
 <%@ page import="java.util.List" %>	
  <%@ page import="com.amit.util.*" %>
 
    <%
    if(session == null || session.getAttribute("isValid") == null || session.getAttribute("isValid") != "true") {
    	String indexPage = "./index.jsp";
    	response.sendRedirect(indexPage);
    }
    List<Friend> friendList = HelperClass.getFriends((int) session.getAttribute("id"));
    int activeFriendId = session.getAttribute("activeChat") != null ?  Integer.parseInt(session.getAttribute("activeChat").toString()) : 0;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Insert title here</title>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
.messageBox{
display:none;
}

.active{
display:block;
}

.floatRight{
float:right
}
</style>
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

<div class="bg-blue-200 h-screen w-auto sm:w-auto md:w-1/2 lg:w-1/4 flex flex-col divide-y divide-green-500 float-left overflow-y-auto">
	 <% if(friendList != null){ 
	 	for(int i = 0; i < friendList.size(); i++) { %>
	<button id="friends" onclick="openMessageBox(event, '<%out.print(friendList.get(i).getId()); %>')" class=" hover:bg-red-100 hover:shadow-lg">
		<!-- <td><% out.println(friendList.get(i).getName());  out.println(friendList.get(i).getImg()); %></td> -->
		<img alt="" src="<%out.println(friendList.get(i).getImg()); %>" class="float-left ml-3 h-16 w-16 p-2 rounded-full ">
		<div class="grid float-left mt-2 ml-5 text-left">
			<label class="font-serif text-2xl  "><%out.println(friendList.get(i).getName()); %></label>
			<label class="font-serif text-md  ">Online</label>
		</div>
	</button>
<% }} %>
</div>	

 <% if(friendList != null){ 
	 	for(int i = 0; i < friendList.size(); i++) { %>
	 	
	<div id="<% out.print(friendList.get(i).getId()); %>" class="messageBox<% if(activeFriendId == friendList.get(i).getId()) {out.print(" active"); }%>">
		<h3><%out.println(friendList.get(i).getId()); %></h3>
 	 	<h3><%out.println(friendList.get(i).getName()); %></h3>
 	  <% 
 	  
 	  for(int j = 0; j < friendList.get(i).getChat().size(); j++) { %>
 	 		 	
 	 		 	<h1 class="<% if(friendList.get(i).getChat().get(j).isSended()) {out.print("floatRight"); }  %>"><% out.println(friendList.get(i).getChat().get(j).getMessage());%></h1><br>
 	 		 	
 	 		 	<% } %>
 	 	
 		<form action="MessageServlet" method="post">
 	 	<input type="hidden" name="friendId" value="<% out.print(friendList.get(i).getId()); %>" />
  		<input type="text" name="message" />
  		<input type="submit" value="Send"></form>
	</div>	
<% }} %>

<script>
function openMessageBox(evt,friendName) {
	 var i, tabcontent, tablinks;
	  tabcontent = document.getElementsByClassName("messageBox");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }
	  tablinks = document.getElementsByClassName("friends");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }
	  document.getElementById(friendName).style.display = "block";
	  evt.currentTarget.className += " active";
}
</script>
</body>
</html>