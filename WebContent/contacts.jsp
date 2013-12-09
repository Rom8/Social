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
<%@ page session="false" %>
<%
Person person = (Person) request.getAttribute(Links.PERSON);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SN - <%=person.getFirstName() %>'s friends</title>
</head>
<table border="1" cellspacing="15" frame="void">
		<tr>
			<td bgcolor="#2E64FE">
				<%=JSPHelper.getButton("Friends", "personID", String.valueOf(person.getPersonId()),
				"circleID", person.getAccessCircleByName(RelationType.FRIENDS.toString()).getName())
				%>
			</td>
			<td bgcolor="#D7DF01">Requests</td>
			<td bgcolor="#7401DF">My requests</td>
		</tr>
		<tr>
			<td bgcolor="#0080FF">Followers</td>
			<td bgcolor="#BCF5A9">I Follow</td>
			<td bgcolor="#DF0101">Banned</td>
		</tr>
		<tr>
			<td colspan="3"><a href="/Social/id<%=person.getPersonId() %>/circles">Circles...</a></td>
		</tr>
</table>
</html>