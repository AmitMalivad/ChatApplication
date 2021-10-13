<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index page</title>
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="grid place-items-center h-screen bg-gray-200">
	<form action="SendOTPServlet" method="post" class="bg-white p-20 rounded-3xl">
  		<h1 class="text-center text-4xl tracking-wide from-current">OTP Verification</h1><br>
    	<pre class="tracking-wide text-gray-600 text-sm font-sans leading-5 text-center">We will send you an <b>One Time Password</b><br>on this mobile number</pre>
    	<%
    		session = request.getSession(false);
    		if(session.getAttribute("otpMessage") != null) {
        		String otpMessage = (String)session.getAttribute("otpMessage");
        %>		
    	<br>
    	<label class="text-red-600 font-serif text-lg"><%out.print(otpMessage); }%></label><br>
    	<label class="text-gray-600 tracking-wider whitespace-normal text-sm">Enter Mobile Number</label><br>
		<input type="text" name="mobileNumber" class="border-yellow-600 border-b-2 container hover:border-green-600 border-b-2"><br><br><br>
    	<input type="submit" value="Get OTP" class="bg-purple-600 container p-1 rounded-3xl hover:bg-purple-800 text-white font-bold font-serif font-normal">
	</form>
</div>
</body>
</html>