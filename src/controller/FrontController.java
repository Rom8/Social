package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.entitymanagers.AccessHelper;
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
		String oldURI = request.getParameter("oldURI");
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
		String unfollow = request.getParameter("unfollow");
		String cancelFriendRequest = request.getParameter("cancelFriendRequest");
		String unban = request.getParameter("unban");
		String divorce = request.getParameter("divorce");

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
			sendFriendRequest(request, response, friendRequest, oldURI);
		}else
		
		if(confirmFriendRequest != null){
			confirmRequest(request, response, confirmFriendRequest, oldURI);
		}else
			
		if(follow != null){
			followPerson(request, response, follow, oldURI);
		}else
			
		if(unfollow != null){
			unfollowPerson(request, response, unfollow, oldURI);
		}else
			
		if(keepAsFollower != null){
			keepHumanAsFollower(request, response, keepAsFollower, oldURI);
		}else
			
		if(cancelFriendRequest != null){
			cancelFriendRequest(request, response, cancelFriendRequest, oldURI);
		}else
			
		if(unban != null){
			unban(request, response, unban, oldURI);
		}else
		
		if(divorce != null){
			divorce(request, response, unban, oldURI);
		}else	
		
		if (request.getSession().getAttribute(Links.PERSON_ID) == null) {
			response.sendRedirect("welcome.jsp");
		}else response.sendRedirect("id" + 
			((long)request.getSession().getAttribute(Links.PERSON_ID)));
	}
	
	protected void divorce(HttpServletRequest request,
			HttpServletResponse response, String divorce, String oldURI)
					throws ServletException, IOException {
		long personId = Long.valueOf(divorce);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );

		removePersonFromCircle(RelationType.LOVER.toString(), owner, person);
		removePersonFromCircle(RelationType.LOVER.toString(), person, owner);		
		response.sendRedirect(oldURI);
	}
	
	protected void unban(HttpServletRequest request,
			HttpServletResponse response, String unban, String oldURI)
					throws ServletException, IOException {
		long personId = Long.valueOf(unban);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );

		removePersonFromCircle(RelationType.BANNED.toString(), owner, person);
		response.sendRedirect(oldURI);
	}
	
	protected void cancelFriendRequest(HttpServletRequest request,
			HttpServletResponse response, String cancelFriendRequest, String oldURI)
					throws ServletException, IOException {
		long personId = Long.valueOf(cancelFriendRequest);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		removePersonFromCircle(RelationType.MYREQUESTS.toString(), owner, person);
		removePersonFromCircle(RelationType.REQUESTED.toString(), person, owner);
		response.sendRedirect(oldURI);
	}
	
	protected void unfollowPerson(HttpServletRequest request,
			HttpServletResponse response, String unfollow, String oldURI)
				throws ServletException, IOException {
		long personId = Long.valueOf(unfollow);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		removePersonFromCircle(RelationType.IFOLLOW.toString(), owner, person);
		removePersonFromCircle(RelationType.FOLLOWERS.toString(), person, owner);
		response.sendRedirect(oldURI);
	}
	
	protected void followPerson(HttpServletRequest request,
	HttpServletResponse response, String follow, String oldURI) 
				throws ServletException, IOException {
		long personId = Long.valueOf(follow);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		addPersonToCircle(RelationType.IFOLLOW.toString(), owner, person);
		addPersonToCircle(RelationType.FOLLOWERS.toString(), person, owner);
		response.sendRedirect(oldURI);
	}
	
	protected void keepHumanAsFollower(HttpServletRequest request,
			HttpServletResponse response, String keepAsFollower, String oldURI) 
					 				throws ServletException, IOException {
		long personId = Long.valueOf(keepAsFollower);
		Person person = es.getPersonById(personId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		removePersonFromCircle(RelationType.REQUESTED.toString(), owner, person);
		removePersonFromCircle(RelationType.MYREQUESTS.toString(), person, owner);
		
		removePersonFromCircle(RelationType.FRIENDS.toString(), owner, person);
		removePersonFromCircle(RelationType.FRIENDS.toString(), person, owner);
		
		addPersonToCircle(RelationType.FOLLOWERS.toString(), owner, person);
		addPersonToCircle(RelationType.IFOLLOW.toString(), person, owner);
		response.sendRedirect(oldURI);
	}
	
	protected void confirmRequest(HttpServletRequest request,
			HttpServletResponse response, String confirmFriendRequest, String oldURI) 
					 				throws ServletException, IOException {
		long newFriendId = Long.valueOf(confirmFriendRequest);
		Person person = es.getPersonById(newFriendId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		
		removePersonFromCircle(RelationType.REQUESTED.toString(), owner, person);
		removePersonFromCircle(RelationType.MYREQUESTS.toString(), person, owner);
		
		removePersonFromCircle(RelationType.FOLLOWERS.toString(), owner, person);
		removePersonFromCircle(RelationType.IFOLLOW.toString(), person, owner);
		
		addPersonToCircle(RelationType.FRIENDS.toString(), owner, person);
		addPersonToCircle(RelationType.FRIENDS.toString(), person, owner);
		response.sendRedirect(oldURI);
	}
	
	private void addPersonToCircle(String circleName, Person owner, Person person){
		GenericManager<AccessCircle> circleManager = AccessHelper.getGenericManager();
		circleManager.setEntity( es.getCircle(circleName, owner) );
		circleManager.getEntity().addPerson(person);
		circleManager.save();
	}
	
	private void removePersonFromCircle(String circleName, Person owner, Person person){
		GenericManager<AccessCircle> circleManager = AccessHelper.getGenericManager();
		circleManager.setEntity( es.getCircle(circleName, owner) );
		circleManager.getEntity().getPersons().remove(person);
		circleManager.save();
	}
	
	protected void sendFriendRequest(HttpServletRequest request,
			HttpServletResponse response, String friendRequest, String oldURI) throws ServletException, IOException{
		long consumerId = Long.valueOf(friendRequest);
		Person person = es.getPersonById(consumerId);
		HttpSession session = request.getSession(false);
		Person owner = es.getPersonById( (long) session.getAttribute(Links.PERSON_ID) );
		addPersonToCircle(RelationType.MYREQUESTS.toString(), owner, person);
		addPersonToCircle(RelationType.REQUESTED.toString(), person, owner);
		response.sendRedirect(oldURI);
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
			Helper.redirectSingedInToMyPage(session, response);
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
