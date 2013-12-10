<%@page import="controller.Links"%>
<%@page import="persistence.model.Person"%>
<%@page import="persistence.model.AccessCircle"%>
<%@page import="controller.Helper"%>
<%
long personID = Long.parseLong( Helper.getSessionPersonId(session) );
Person person = Helper.getPersonByID(personID);
%>