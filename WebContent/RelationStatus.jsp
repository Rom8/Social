<%@page import="persistence.model.RelationType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="persistence.model.Person" %>
<%@ page import="controller.Links" %>
<%@ page import="controller.Helper" %>
<%@ page session="false" %>

<%
Person p = (Person) request.getAttribute(Links.PERSON);
HttpSession session = request.getSession(false);
if(session == null){
%>
	Please, <a href="signin.jsp">sign in</a> if you want to contact with <%=p.getFirstName() %>
<% 
}else if(Helper.isMyPage(request)){
%>
	<i>My page</i>
<%
}else if(Helper.getRelationStatus(request) != null ){
	RelationType relationType = Helper.getRelationStatus(request);
	switch(relationType) {
		case FRIENDS:    
			out.println(p.getFirstName() + " is in your FRIENDS list");
			break;
		case MYREQUESTS:
			out.println(p.getFirstName() + " wants to be your friend");
			break;
		case REQUESTED:
			out.println(p.getFirstName() + " has not yet considered your friend request");
			break;
		case FOLLOWERS:
			out.println("You are following  " + p.getFirstName());
			break;
		case IFOLLOW:
			out.println(p.getFirstName() + " is following you");
			break;
		case LOVER:
			out.println("You are in love with " + p.getFirstName());
			break;
		case BANNED:
			out.println(p.getFirstName() + " doesn't want to communicate with you");
			break;
	}
}else{
%>
	<form action="/Social/FrontController" method="post">
		<input type="hidden" name="friendRequest"
			value="<%=p.getPersonId() %>">
		<input type="submit" value="Send Friend Request">
	</form>
	<form action="/Social/FrontController" method="post">
		<input type="hidden" name="follow"
			value="<%=p.getPersonId() %>">
		<input type="submit" value="Follow">
	</form>
	
<%
}
%>