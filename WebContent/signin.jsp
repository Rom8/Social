<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SN - Sign In</title>
</head>
<body>
<% if(request.getParameter("signNew") != null) { %>
	 <%= request.getParameter("signNew") %>
<%
}else{ %> Type your email and password <br> <% } %>
	<form action="FrontController" method="post"
		style="height: 111px; width: 345px;">
		<table>
			<tr>
				<td>E-mail</td>
				<td><input name="email"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="password" type="password"></td>
			</tr>
		</table>
		<input type="submit" value="Sign in">
		<input type="reset" value="Clear">
	</form>
</body>
</html>