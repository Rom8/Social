<%@page import="controller.Links"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% if(session.getAttribute(Links.PERSON_ID) != null){ %>
	<jsp:include page="headerForSignedInUser.jsp"></jsp:include>
<% } else { %>
	<jsp:include page="headerForUnSignedInUser.jsp"></jsp:include>
<% } %>