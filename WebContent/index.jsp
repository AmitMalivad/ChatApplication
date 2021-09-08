<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    if(session.getAttribute("message") != null) {
    	session = request.getSession(false);
        String message=(String)session.getAttribute("message");  
        out.print(message);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="SendOTPServlet" method="post">
<input type="text" name="mobileNumber">
<input type="submit" value="SendOTP">
</form>
</body>
</html>