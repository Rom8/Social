<%@page import="javax.jms.Session"%>
<%@page import="controller.Helper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<% Helper.redirectSingedInToMyPage(request, response); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SN - Welcome Page</title>
</head>
<body>
Welcome to SN! Please, <a href='signin.jsp'> sign in </a> or <a href='signup.jsp'> sign up </a>.
</body>
</html>