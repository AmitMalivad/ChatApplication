<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
    session = request.getSession(false);
    String mobileNumber = (String)session.getAttribute("mobileNumber");
    System.out.println(mobileNumber);
    if(session == null || session.getAttribute("mobileNumber") == null) {
    	String indexPage = "./index.jsp";
    	response.sendRedirect(indexPage);
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="grid place-items-center h-screen bg-gray-200">
		<form action ="ValidateOTPServlet" method="post" class="bg-white p-20 rounded-3xl">
  			<h1 class="text-center text-4xl tracking-wide from-current">OTP Verification</h1><br>
    		<pre class="tracking-wide text-gray-600 text-sm font-sans leading-5 ">Enter the OTP send to : <b><% out.print(mobileNumber);%></b></pre><br>
    		<label class="text-gray-600 tracking-wider whitespace-normal text-sm">Enter 4 digit</label><br>
			<input type="text" name="userOTP" class="border-yellow-600 border-b-2 container hover:border-green-600 border-b-2"><br><br><br>
			<input type="submit" value="OTP" class="bg-purple-600 container p-1 rounded-3xl hover:bg-purple-800 text-white font-bold font-serif font-normal">
		</form>
	</div>
</body>
</html>