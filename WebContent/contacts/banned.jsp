<%@page import="java.util.List"%>
<%@page import="persistence.model.RelationType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banned</title>
</head>
<body>
<%= person.getFirstName() %>
<br>
<%
AccessCircle circle = person.getAccessCircleByName(RelationType.BANNED.toString());
%>


</body>
</html>