<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SN - Sing Up</title>
</head>
<body>
<% if(request.getParameter("signNew") != null) { %>
	 <%= request.getParameter("signNew") %>
<%
}else{ %> Type your email, password and first name <br> <% } %>
	<form action="FrontController" method="post"
		style="height: 111px; width: 345px;">
		<table>
			<tr>
				<td>E-mail<font color="red">*</font></td>
				<td><input name="emailSingUp"></td>
			</tr>
			<tr>
				<td>Password<font color="red">*</font></td>
				<td><input name="passwordFirst" type="password"></td>
			</tr>
			<tr>
				<td>Confirm password<font color="red">*</font></td>
				<td><input name="passwordSecond" type="password"></td>
			</tr>
			<tr>
				<td>First name<font color="red">*</font></td>
				<td><input name="firstName"></td>
			</tr>
		</table>
		<input type="submit" value="Sign up">
		<input type="reset" value="Clear">
	</form>
</body>
</html>