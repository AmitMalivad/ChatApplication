<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    session = request.getSession(false);
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
</head>
<body>
<form action ="ValidateOTPServlet" method="post">
<input type="text" name="userOTP">
<input type="submit" value="OTP">
</form>
</body>
</html>