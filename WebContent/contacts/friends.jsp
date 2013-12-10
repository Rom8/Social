<%@page import="helpers.JSPHelper"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
<%
AccessCircle circle = person.getAccessCircleByName(RelationType.FRIENDS.toString());
List<Person> persons = new ArrayList<Person>(circle.getPersons());
Collections.sort(persons);
for(Person p: persons){
%>
	<%= p.getFirstName() %>
	<%= p.getLastName() %>
	<%= JSPHelper.getButton( "Keep as follower",
							 "keepAsFollower", String.valueOf(p.getPersonId()),
							 "oldURI", request.getRequestURI()
							) %>
<%
}
%></body>
</html>