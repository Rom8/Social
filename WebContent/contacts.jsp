<%@page import="helpers.JSPHelper"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="persistence.model.AccessCircle"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="controller.Helper"%>
<%@page import="controller.Links"%>
<%@page import="persistence.model.RelationType" %>
<%@page	import="persistence.entitymanagers.EntityService" %>
<%@page	import="java.util.Map" %>
<%@page	import="java.util.Set" %>
<%@page	import="persistence.model.Person" %>
<%@page	contentType="text/html; charset=ISO-8859-1" %>
<%@page pageEncoding="ISO-8859-1"%>
<%@ include file="contacts/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SN - <%=person.getFirstName() %>'s contacts</title>
</head>
<table border="1" cellspacing="15" frame="void">
		<tr>
			<td><a href="/Social/contacts/friends.jsp">Friends</a></td>
			<td><a href="/Social/contacts/requests.jsp">Friend requests</a></td>
			<td><a href="/Social/contacts/myrequests.jsp">My requests</a></td>
			<td><a href="/Social/contacts/followers.jsp">Followers</a></td>
			<td><a href="/Social/contacts/ifollow.jsp">I follow</a></td>
			<td><a href="/Social/contacts/banned.jsp">Banned</a></td>
		</tr>
		<tr>
			<td colspan="3"><a href="/Social/id<%=person.getPersonId() %>/circles">Circles...</a></td>
		</tr>
</table>
</html>