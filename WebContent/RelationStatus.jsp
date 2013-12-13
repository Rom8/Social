<%@page import="persistence.model.AccessCircle"%>
<%@page import="java.util.List"%>
<%@page import="helpers.JSPHelper"%>
<%@page import="persistence.model.RelationType"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="persistence.model.Person" %>
<%@ page import="controller.Links" %>
<%@ page import="controller.Helper" %>

<%
boolean flaq = true;			//better to avoid of using flaq
Person p = (Person) request.getAttribute(Links.PERSON);
if(session.getAttribute(Links.PERSON_ID) == null){
	flaq = false;
%>
	Please, <a href="signin.jsp">sign in</a> if you want to contact with <%=p.getFirstName() %>
<% 
}else if(Helper.isMyPage(request)){
%>
	<i>My page</i>
<%
}else if(session.getAttribute(Links.PERSON_ID) != null){
	Person owner = Helper.getEntityService().getOwner(session);
	List<AccessCircle> list = Helper.getEntityService().getCircles(owner, p);
	for(AccessCircle ac: list){
		if(ac.getName().matches("(FRIENDS)|(REQUESTED)|(MYREQUESTS)|(FOLLOWERS)|(IFOLLOW)|(LOVER)|(BANNED)")){
			flaq = false;
			RelationType r = RelationType.valueOf(ac.getName());
			switch(r) {
				case FRIENDS:
					out.println(p.getFirstName() + " is in your FRIENDS list");
					break;
				case MYREQUESTS:
					out.println(p.getFirstName() + " has not yet considered your friend request");
					break;
				case REQUESTED:
					out.println(p.getFirstName() + " wants to be your friend");
					break;
				case FOLLOWERS:
					out.println(p.getFirstName() + " is following you");
					break;
				case IFOLLOW:
					out.println("You are following  " + p.getFirstName());
					break;
				case LOVER:
					out.println("You are in love with " + p.getFirstName());
					break;
				case BANNED:
					out.println(p.getFirstName() + " is in your BAN list.");
					break;
			}
		}else{
			out.println(p.getFirstName() + " is also consists in your " + ac.getName());
		}
	}
}

if(flaq && !p.equals(Helper.getEntityService().getOwner(session))){
%>
<%= JSPHelper.getButton("Send Friend Request",
						"friendRequest", String.valueOf(p.getPersonId()),
						"oldURI", request.getRequestURI())  %>
<%= JSPHelper.getButton("Follow",
						"follow", String.valueOf(p.getPersonId()),
						"oldURI", request.getRequestURI())  %>
<%
}
%>