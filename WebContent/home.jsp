<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    if(session == null || session.getAttribute("isValid") == null || session.getAttribute("isValid") != "true") {
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
<h2>WelCome to my ChatApplication</h2>
</body>
</html>