<%@page import="persistence.model.RelationType"%>
<%@page import="helpers.CircleHelper"%>
<%@page import="persistence.model.Person"%>
<%@page import="controller.Helper"%>
<%@page import="persistence.entitymanagers.EntityService"%>
<%@page import="persistence.model.AccessCircle"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
EntityService es = Helper.getEntityService();
Person owner = es.getOwner(session);
String circleName = request.getParameter("circleName");
AccessCircle circle = es.getCircle(circleName, owner);
if(circleName.matches("(FRIENDS)|(REQUESTED)|(MYREQUESTS)|"
						+ "(FOLLOWERS)|(IFOLLOW)|(LOVER)|(IAMINBAN)|(BANNED)")){
	circleName = RelationType.valueOf(circleName).nameLine();
}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= circleName %></title>
</head>
<body>
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<%=circleName %> <br>
	<%= CircleHelper.printCircle(circle, request.getRequestURI()
								+ "?circleName=" + circle.getName()) %>
</body>
</html>