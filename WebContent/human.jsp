<%@ page import="controller.Links" %>
<%@ page import="controller.Helper "%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%@ page import="persistence.model.Person" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	if(request.getAttribute(Links.PERSON) == null){
	request.getRequestDispatcher("includes/PageDoesNotExist.jsp").forward(request, response);
}else{
Person person = (Person) request.getAttribute(Links.PERSON);
%>
<title>
		SN - <%= person.getFirstName() %>
</title>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<%@ include file="RelationStatus.jsp" %>
	<table>
		<tr>
			<td>
				<table>
					<tr>
						<td><a href= 'id<%= person.getPersonId() %>/contacts' > Contacts </a></td>
					</tr>
					<tr>
						<td>Messages</td>
					</tr>
					<tr>
						<td>Photos</td>
					</tr>
					<tr>
						<td>Notes</td>
					</tr>
				</table>
			</td> 
			<td>
				<table>
			<tr>
				<td><b>Attribute</b></td> 
				<td><b>Value</b></td>
			</tr>
			<tr>
				<td>First name</td>
				<td><%= person.getFirstName() %></td>
			</tr>
			<tr>
				<td>Middle name</td> 
				<td><%= person.getMiddleName() %></td>
			</tr>
			<tr>
				<td>Last name</td>
				<td><%= person.getLastName() %></td>
			</tr>
			<tr>
				<td>Birthday</td> 
				<td>
<%
SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); 
Date birthdayDate = person.getBirthday();
String birthday = birthdayDate != null ? dateFormat.format(birthdayDate) : "Untyped";
%>
				<%= birthday %>
				</td>
			</tr>
			<tr>
				<td>Hometown</td>
				<td><%= person.getHometown() %></td>
			</tr>
  	</table>
			</td>
		</tr>	
	</table>
	
</body>
</html>
<% } %>