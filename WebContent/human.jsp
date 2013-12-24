<%@page import="persistence.model.RelationType"%>
<%@page import="logic.relation.RelationStatus"%>
<%@page import="persistence.entitymanagers.AccessHelper"%>
<%@page import="persistence.entitymanagers.EntityService"%>
<%@ page import="controller.Links"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="persistence.model.Person"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%
	EntityService es = AccessHelper.getEntityService();
	String personId = (String) request.getAttribute(Links.PERSON_ID);
	Person person = es.getPersonById(Long.parseLong(personId));
	if(person == null){
%>
	<title>SN - User with id <%=personId%> does not exist
	</title>
	</head>
	<body>
		<jsp:include page="includes/header.jsp"></jsp:include>
		User with id <%=personId %> does not exist
<%
	}else{
		RelationStatus relationStatus = new RelationStatus(session, person);
		RelationType relationType = relationStatus.getRelationType();
%>


<title>SN - <%=person.getFirstName()%>
</title>
</head>
<body>
	<jsp:include page="includes/header.jsp"></jsp:include>
		<%=relationType.lineForPerson(person) %>
		<%=relationType.getButtons(person, "/Social/id" + person.getPersonId()) %>

	<table>
		<tr>
			<td><b>Attribute</b></td>
			<td><b>Value</b></td>
		</tr>
		<tr>
			<td>First name</td>
			<td><%=person.getFirstName()%></td>
		</tr>
		<tr>
			<td>Middle name</td>
			<td><%=person.getMiddleName()%></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><%=person.getLastName()%></td>
		</tr>
		<tr>
			<td>Birthday</td>
			<td>
				<%
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
						Date birthdayDate = person.getBirthday();
						String birthday = birthdayDate != null ? dateFormat
								.format(birthdayDate) : "Untyped";
				%> <%=birthday%>
			</td>
		</tr>
		<tr>
			<td>Hometown</td>
			<td><%=person.getHometown()%></td>
		</tr>
	</table>

<%
}
 %>
</body>
</html>