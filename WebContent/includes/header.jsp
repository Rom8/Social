<%@ page import="controller.Helper" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<% if(request.getSession(false) != null){ %>
	<jsp:include page="headerForSignedInUser.jsp"></jsp:include>
<% } else { %>
	<jsp:include page="headerForUnSignedInUser.jsp"></jsp:include>
<% } %>