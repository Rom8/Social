<%@page import="persistence.entitymanagers.AccessHelper"%>
<%@page import="java.util.List"%>
<%@page import="persistence.entitymanagers.EntityService"%>
<%@page import="persistence.model.Person"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
EntityService es = AccessHelper.getEntityService();
String mutual = request.getParameter("mutual");
long personId = Long.parseLong(mutual);
Person person = es.getPersonById(personId);
Person owner = es.getOwner(session);
List<Person> list = es.getMutualfriends(owner, person);
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mutual friends with</title>
</head>
<body>
<jsp:include page="/includes/header.jsp"></jsp:include>
<%
for(Person p: list){ %>
<a href="/Social/id<%= p.getPersonId() %>"><%= p.getFirstName() %></a><br>
<%	
}
 %>
</body>
</html>