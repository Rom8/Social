package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.entitymanagers.EntityService;
import persistence.entitymanagers.GenericManager;
import persistence.model.AccessCircle;
import persistence.model.Person;
import persistence.model.RelationType;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 6L;
	@EJB
	EntityService es;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String emailSingUp = request.getParameter("emailSingUp");
		String passwordFirst = request.getParameter("passwordFirst");
		String passwordSecond = request.getParameter("passwordSecond");
		String firstName = request.getParameter("firstName");
		String signOut = request.getParameter("signOut");
		String friendRequest = request.getParameter("friendRequest");
		String confirmFriendRequest = request.getParameter("confirmFriendRequest");
		String keepAsFollower = request.getParameter("keepAsFollower");
		String follow = request.getParameter("follow");

		if (request.getAttribute(Links.PERSON_ID) != null) {
			dispatch(request, response, Links.PERSON_ID, "human.jsp");
		}else
			
		if (request.getAttribute(Links.PERSON_FRIENDS_ID) != null) {
			System.err.println("person_friends_id when???");
//			dispatch(request, response, Links.PERSON_FRIENDS_ID, "contacts.jsp");
		}else
			
		if(request.getAttribute(Links.PERSON_CIRCLES_ID) != null){
			System.err.println("person_circle_id when???");
//			dispatch(request, response, Links.PERSON_CIRCLES_ID, "friends_include/AccessCircles.jsp");
		}else
			
		if ((email != null) && (password != null)) {
			singIn(request, response, email, password);
		}else
		
		if(	
			(emailSingUp != null) &&
			(passwordFirst != null) &&
			(passwordSecond != null) &&
			(firstName!= null)
			){
			singUp(request, response, emailSingUp, passwordFirst, passwordSecond, firstName);
		}else
		
		if(signOut != null){
			signOut(request, response);
		}else
			
		if(friendRequest != null){
			sendFriendRequest(request, response, friendRequest);
		}else
		
		if(confirmFriendRequest != null){
			confirmRequest(request, response, confirmFriendRequest);
		}else
			
		if(follow != null){
			followPerson(request, response, follow);
		}else
			
		if(keepAsFollower != null){
			keepHumanAsFollower(request, response, keepAsFollower);
		}else
			
		if (request.getSession(false) == null) {
			response.sendRedirect("welcome.jsp");
		}else response.sendRedirect("id" + 
			((long)request.getSession(false).getAttribute(Links.PERSON_ID)));
	}
	
	protected void followPerson(HttpServletRequest request,
	HttpServletResponse response, String follow) 
				throws ServletException, IOException {
		long personId = Long.valueOf(follow);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		addPersonToCircle(RelationType.IFOLLOW, owner, person);
		addPersonToCircle(RelationType.FOLLOWERS, person, owner);
		
		response.sendRedirect("/Social/id" + person.getPersonId());
	}
	
	protected void keepHumanAsFollower(HttpServletRequest request,
			HttpServletResponse response, String keepAsFollower) 
					 				throws ServletException, IOException {
		long personId = Long.valueOf(keepAsFollower);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		removePersonFromCircle(RelationType.REQUESTED, owner, person);
		removePersonFromCircle(RelationType.MYREQUESTS, person, owner);
		
		removePersonFromCircle(RelationType.FRIENDS, owner, person);
		removePersonFromCircle(RelationType.FRIENDS, person, owner);
		
		addPersonToCircle(RelationType.FOLLOWERS, owner, person);
		addPersonToCircle(RelationType.IFOLLOW, person, owner);
		
		response.sendRedirect("/Social/id" + owner.getPersonId() + "/contacts");
	}
	
	protected void confirmRequest(HttpServletRequest request,
			HttpServletResponse response, String confirmFriendRequest) 
					 				throws ServletException, IOException {
		long newFriendId = Long.valueOf(confirmFriendRequest);
		Person person = es.getPersonById(newFriendId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		removePersonFromCircle(RelationType.REQUESTED, owner, person);
		addPersonToCircle(RelationType.FRIENDS, owner, person);
		removePersonFromCircle(RelationType.MYREQUESTS, person, owner);
		addPersonToCircle(RelationType.FRIENDS, person, owner);
		
		response.sendRedirect("/Social/id" + owner.getPersonId() + "/contacts");
	}
	
	private void addPersonToCircle(RelationType relationType, Person owner, Person person){
		try {
			InitialContext initialContext = new InitialContext();
			
			@SuppressWarnings("unchecked")
			GenericManager<AccessCircle> circle = 
					(GenericManager<AccessCircle>) initialContext.lookup("java:comp/env/GenericManager");
			circle.setEntity(es.getCircle(
					relationType.toString(), owner));
			circle.getEntity().addPerson(person);
			circle.save();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private void removePersonFromCircle(RelationType relationType, Person owner, Person person){
		try {
			InitialContext initialContext = new InitialContext();
			
			@SuppressWarnings("unchecked")
			GenericManager<AccessCircle> circle = 
					(GenericManager<AccessCircle>) initialContext.lookup("java:comp/env/GenericManager");
			circle.setEntity(es.getCircle(
					relationType.toString(), owner));
			circle.getEntity().getPersons().remove(person);
			circle.save();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	protected void sendFriendRequest(HttpServletRequest request,
			HttpServletResponse response, String friendRequest) throws ServletException, IOException{
		long consumerId = Long.valueOf(friendRequest);
		Person person = es.getPersonById(consumerId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		addPersonToCircle(RelationType.MYREQUESTS, owner, person);
		addPersonToCircle(RelationType.REQUESTED, person, owner);

		response.sendRedirect("id" + consumerId);
	}
	
	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response, String link, String page) throws ServletException, IOException {
		long id = Long.parseLong((String) request.getAttribute(link));
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		Person person = es.getPersonById(id);
		request.setAttribute(Links.PERSON, person);
		dispatcher.forward(request, response);
	}
	
	protected void singIn(HttpServletRequest request,
			HttpServletResponse response, 
			String email, String password) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		Person person = es.singPersonIn(email, password);
		if (person != null) {
			session = request.getSession(true);
			synchronized (session) {
				session.setAttribute(Links.PERSON_ID, Long.valueOf(person.getPersonId()));
			}
			Helper.redirectSingedInToMyPage(request, response);
		} else
			response.sendRedirect(
					"signin.jsp?signNew=Combination+of+such+email+and+password+was+not+found.");
	}

	protected void singUp(HttpServletRequest request,
			HttpServletResponse response, 
			String emailSingUp, String passwordFirst,
			String passwordSecond, String firstName) throws ServletException, IOException{
		if(es.isEmailExist(emailSingUp))
			response.sendRedirect("signup.jsp?signNew=Email+" + emailSingUp + "+is+already+used");
		else 
		if(passwordFirst.equals(passwordSecond)){
			es.createNewPerson(emailSingUp, passwordSecond, firstName);
			response.sendRedirect("signin.jsp?signNew=Well+done!+Now+use+your+email+and+password");
		}else{
			response.sendRedirect("signup.jsp?signNew=Passwords+are+not+equal");
		}
	}
	
	protected void signOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String personPage = "/Social/id" + Helper.getSessionPersonId(request.getSession(false));
		request.getSession(false).invalidate();
		response.sendRedirect(personPage);
	}
}
