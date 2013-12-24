<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="persistence.model.AccessCircle"%>
<%@page import="persistence.model.RelationType"%>
<%@page import="persistence.model.Person"%>
<%@page import="controller.Helper"%>
<%@page import="persistence.entitymanagers.EntityService"%>
<%@page	contentType="text/html; charset=ISO-8859-1" %>
<%@page pageEncoding="ISO-8859-1"%>
<%
EntityService es = Helper.getEntityService();
Person owner = es.getOwner(session);
Set<AccessCircle> circleList = owner.getAccessCircles();
Map<String, Integer> circleSizeMap = new HashMap<String, Integer>(circleList.size(), 1);
for(AccessCircle ac: circleList){
	circleSizeMap.put(ac.getName(), Integer.valueOf(ac.getPersons().size()));
}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SN - <%=owner.getFirstName() %>'s contacts</title>
</head>
<body>
<jsp:include page="includes/header.jsp"></jsp:include>
<table border="1" cellspacing="15" frame="void">
		<tr>
			<td>
				<a href="/Social/contacts/circle.jsp?circleName=FRIENDS">
					<%=RelationType.FRIENDS.nameLine() %>
					(<%=circleSizeMap.get(RelationType.FRIENDS.toString()).intValue() %>)
				</a>
			</td>
			<td>
				<a href="/Social/contacts/circle.jsp?circleName=REQUESTED">
					<%=RelationType.REQUESTED.nameLine() %>
					(<%=circleSizeMap.get(RelationType.REQUESTED.toString()).intValue() %>)
				</a>
			</td>
			<td>
				<a href="/Social/contacts/circle.jsp?circleName=MYREQUESTS">
					<%=RelationType.MYREQUESTS.nameLine() %>
					(<%=circleSizeMap.get(RelationType.MYREQUESTS.toString()).intValue() %>)
				</a>
			</td>
			<td>
				<a href="/Social/contacts/circle.jsp?circleName=FOLLOWERS">
					<%=RelationType.FOLLOWERS.nameLine() %>
					(<%=circleSizeMap.get(RelationType.FOLLOWERS.toString()).intValue() %>)
				</a>
			</td>
			<td>
				<a href="/Social/contacts/circle.jsp?circleName=IFOLLOW">
					<%=RelationType.IFOLLOW.nameLine() %>
					(<%=circleSizeMap.get(RelationType.IFOLLOW.toString()).intValue() %>)
				</a>
			</td>
			<td>
				<a href="/Social/contacts/circle.jsp?circleName=BANNED">
					<%=RelationType.BANNED.nameLine() %>
					(<%=circleSizeMap.get(RelationType.BANNED.toString()).intValue() %>)
				</a>
			</td>
		</tr>
</table>
</body>
</html>