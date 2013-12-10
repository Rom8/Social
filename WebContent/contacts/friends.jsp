<%@page import="persistence.model.RelationType"%>
<%@page import="controller.Helper"%>
<%@page import="persistence.model.AccessCircle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friends</title>
</head>
<body>
<%= person.getFirstName() %>
<br>
<%= person.getAccessCircleByName(RelationType.FRIENDS.toString()).getName() %>
</body>
</html>