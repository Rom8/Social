<%@page import="persistence.model.Person"%>
<%@page import="controller.Helper"%>
<%@page import="persistence.entitymanagers.EntityService"%>
<%@page	contentType="text/html; charset=ISO-8859-1" %>
<%@page pageEncoding="ISO-8859-1"%>
<%
EntityService es = Helper.getEntityService();
Person owner = es.getOwner(session);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SN - <%=owner.getFirstName() %>'s contacts</title>
</head>
<table border="1" cellspacing="15" frame="void">
		<tr>
			<td><a href="/Social/contacts/circle.jsp?circleName=FRIENDS">Friends</a></td>
			<td><a href="/Social/contacts/circle.jsp?circleName=REQUESTED">Requests</a></td>
			<td><a href="/Social/contacts/circle.jsp?circleName=MYREQUESTS">My requests</a></td>
			<td><a href="/Social/contacts/circle.jsp?circleName=FOLLOWERS">Follower</a></td>
			<td><a href="/Social/contacts/circle.jsp?circleName=IFOLLOW">I follow</a></td>
			<td><a href="/Social/contacts/circle.jsp?circleName=BANNED">Banned</a></td>
		</tr>
</table>
</html>