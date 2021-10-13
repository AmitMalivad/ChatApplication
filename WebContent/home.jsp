<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="com.amit.dto.*" %>	
 <%@ page import="java.util.List" %>	
  <%@ page import="com.amit.util.*" %>
 
    <%
    if(session == null || session.getAttribute("isValid") == null || session.getAttribute("isValid") != "true") {
    	System.out.println("session is null");
    	String indexPage = "./index.jsp";
    	response.sendRedirect(indexPage);
    	return;
    }
    System.out.println("session is not null");
    List<Friend> friendList = HelperClass.getFriends( session.getAttribute("id") != null ? (int)session.getAttribute("id") : 0);
    int activeFriendId = session.getAttribute("activeChat") != null ?  Integer.parseInt(session.getAttribute("activeChat").toString()) : 0;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1" />
<title>Home page</title>
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
.icon{
font-size:20px;
}
.icon:hover {
  color: purple

}
</style>
</head>
<body>
<div class="bg-gray-800 h-10 w-auto ">
    <label class="font-serif text-xl grid pl-5 p-1 text-yellow-50">Chat Application</label>
 </div>
<div class="bg-green-600 h-52 sm:h-42 md:h-32 lg:h-24">
	<div class="flex float-right mt-6 mr-6 sm:hidden">
		<button class="icon fa fa-bars" onclick="myFunction()"></button>
	</div>
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
	<div id="myLinks" class="display-none sm:flex sm:space-x-7 sm:float-right sm:mt-8 sm:mr-7 ">
		<button  onclick="window.location='profile.jsp'"><i class="icon fa fa-user"> Profile</i></button>
		<button onclick="window.location='addfriend.jsp'"><i class="icon fa fa-user-plus"> Friend</i></button>
		<button onclick="window.location='index.jsp'"><i class="icon fa fa-user-circle"> Logout</i></button>
		<!-- <input type="button" value="ProfileUpdate" onclick="window.location='profile.jsp'"/> -->
	</div>
</div>
	<script>	
			function myFunction() {	
			  var x = document.getElementById("myLinks");
			  if (x.style.display === "block") {
			    x.style.display = "none";
			  } else {
			    x.style.display = "block";			
			  }
			}
	</script>

<div class="flex flex-col divide-y divide-green-500 float-left overflow-y-auto" style='height: 485px;  width: 25%; float: left; background-color: powderblue;'>
	 <% if(friendList != null){ 
	 	for(int i = 0; i < friendList.size(); i++) { %>
	<button id="friends" onclick="openMessageBox(event, '<%out.print(friendList.get(i).getId()); %>')" class=" hover:bg-blue-200 focus:bg-blue-300 hover:shadow-lg">
		<!-- <td><% out.println(friendList.get(i).getName());  out.println(friendList.get(i).getImg()); %></td> -->
		<img alt="" src="<%out.println(friendList.get(i).getImg()); %>" class="float-left ml-3 h-16 w-16 p-2 rounded-full ">
		<div class="grid float-left mt-2 ml-5 text-left">
			<label class="font-serif text-xl  "><%out.println(friendList.get(i).getName()); %></label>
			<label class="font-serif text-sm  ">Online</label>
		</div>
	</button>
<% }} %>
</div>	


 <% if(friendList != null){ 
	 	for(int i = 0; i < friendList.size(); i++) { %>
	 	
	<div id="<% out.print(friendList.get(i).getId()); %>" class="messageBox<% if(activeFriendId == friendList.get(i).getId()) {out.print(" active"); }%> bg-gray-200   w-auto" style='height: 485px;'>
		<div class="  w-auto pl-10 pr-10 pt-10 overflow-y-auto" style='height: 442px;'>
 	  <% 
 	  for(int j = 0; j < friendList.get(i).getChat().size(); j++) { %>	 	
 	 	<h1 class="<% if(friendList.get(i).getChat().get(j).isSended()) {out.print("floatRight"); }  %>"><lable class="text-xl bg-white pt-1 pb-1 pl-5 pr-5 rounded-2xl"><% out.println(friendList.get(i).getChat().get(j).getMessage());%></lable></h1><br><br>
 	  <% } %>
 	 	
 	 		<form action="MessageServlet" method="post" class=" text-center" style='position: absolute; bottom: 5px; right: 20%;' >
 	 			<input type="hidden" name="friendId" value="<% out.print(friendList.get(i).getId()); %>" />
  				<input type="text" placeholder="Message" name="message" class="m-1 p-1 rounded-xl w-96 hover:shadow-lg shadow-inner"/>
  				<input type="submit" value="Send" class="p-1 rounded-2xl w-28 bg-green-600 hover:bg-green-700 hover:shadow-lg">
  			</form>
 	 	</div> 	
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