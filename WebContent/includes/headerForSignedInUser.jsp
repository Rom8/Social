<%@page import="persistence.entitymanagers.AccessHelper"%>
<%@page import="persistence.entitymanagers.EntityService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="controller.Helper" %>
<%
EntityService es = AccessHelper.getEntityService();
 %>
<header>
<table align="center" bgcolor="silver">
	<tr>
		<td width="80">
			<a href="/Social/id<%=es.getOwner(session).getPersonId() %>">My page</a>
		</td>
		<td width="80">
			<a href="/Social/contacts">Contacts</a>
		</td>
		<td width="120">Conversations</td>
		<td width="70">Photos</td>
		<td width="70"> </td>
		<td width="70">
			<form action="/Social/FrontController" method="post">
				<input type="submit" value="Sign Out" name="signOut">
			</form>
		</td>
	</tr>
</table>
</header>