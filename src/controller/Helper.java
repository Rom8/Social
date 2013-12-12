package controller;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.entitymanagers.EntityService;
import persistence.model.AccessCircle;
import persistence.model.Person;
import persistence.model.RelationType;

public class Helper {
	
 	public static void redirectSingedInToMyPage(HttpSession session,
			HttpServletResponse response) throws ServletException, IOException {
		if (session.getAttribute(Links.PERSON_ID.toString()) != null) {
			long personId = (Long) session.getAttribute(Links.PERSON_ID);
			String location = String.valueOf(personId);
			response.sendRedirect("id" + location);
		}
	}
	
	public static boolean isMyPage(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session != null){
			if(session.getAttribute(Links.PERSON_ID) != null){
				return ((Long) session.getAttribute(Links.PERSON_ID))
						.equals( Long.valueOf(((Person) request.getAttribute(Links.PERSON)).getPersonId()));
			}
		}
		return false;
	}
	
	public static String getSessionPersonId(HttpSession session){
		return String.valueOf( (long) session.getAttribute(Links.PERSON_ID) );
	}

	public static boolean isFriend(HttpServletRequest request, HttpSession session){
		try {
			InitialContext context = new InitialContext();
			EntityService es = (EntityService) context.lookup("java:comp/env/EntityService");
			
			Person person = (Person) request.getAttribute(Links.PERSON);
			AccessCircle personCircle = es.getCircle(RelationType.FRIENDS.toString(), person);
			
			Person owner = es.getPersonById(((Long) session.getAttribute(Links.PERSON_ID)).longValue());
			AccessCircle ownerCircle = es.getCircle(RelationType.FRIENDS.toString(), owner);
			
			return (personCircle.getPersons().contains(owner) 
					&& 
					ownerCircle.getPersons().contains(person));
		} catch (NamingException e) {
			System.out.println("naming Exception!!!!");
			e.printStackTrace();
			return false;
		}
	}

	public static RelationType getRelationStatus(HttpServletRequest request){
		try {
			HttpSession session = request.getSession(false);
			InitialContext context = new InitialContext();
			EntityService es = (EntityService) context.lookup("java:comp/env/EntityService");
			Person owner =  es.getPersonById(((Long) session.getAttribute(Links.PERSON_ID)).longValue());
			Person person = (Person) request.getAttribute(Links.PERSON);
			
			AccessCircle circle = es.getCircle(RelationType.FRIENDS.toString(), person);
			if(circle.getPersons().contains(owner))
				return RelationType.FRIENDS;
			
			circle = es.getCircle(RelationType.REQUESTED.toString(), person);
			if(circle.getPersons().contains(owner))
				return RelationType.REQUESTED;
			
			circle = es.getCircle(RelationType.MYREQUESTS.toString(), person);
			if(circle.getPersons().contains(owner))
				return RelationType.MYREQUESTS;
			
			circle = es.getCircle(RelationType.FOLLOWERS.toString(), person);
			if(circle.getPersons().contains(owner))
				return RelationType.FOLLOWERS;
			
			circle = es.getCircle(RelationType.IFOLLOW.toString(), person);
			if(circle.getPersons().contains(owner))
				return RelationType.IFOLLOW;
			
			circle = es.getCircle(RelationType.LOVER.toString(), person);
			if(circle.getPersons().contains(owner))
				return RelationType.LOVER;
			
			circle = es.getCircle(RelationType.BANNED.toString(), person);
			if(circle.getPersons().contains(owner))
				return RelationType.BANNED;
			return null;
		} catch (NamingException e) {
			System.out.println("naming Exception!!!!");
			e.printStackTrace();
			return null;
		}
	}
	
	public static AccessCircle getCircle(long circleID){
		InitialContext context;
		try {
			context = new InitialContext();
			EntityService es = (EntityService) context.lookup("java:comp/env/EntityService");
			return es.getCircle(circleID);
		} catch (NamingException e) {
			System.out.println("Naming Exception in Helper.getCircle");
			e.printStackTrace();
			return null;
		}
	}
	
	public static Person getPersonByID(long personID){
		InitialContext context;
		try {
			context = new InitialContext();
			EntityService es = (EntityService) context.lookup("java:comp/env/EntityService");
			return es.getPersonById(personID);
		} catch (NamingException e) {
			System.out.println("Naming Exception in Helper.getPersonByID");
			e.printStackTrace();
			return null;
		}
	}
/*	
	//returns a "html list" of friends of one type
	public static String friendsColumnType(Person person, String relationType){
		String result = "<td valign=\"top\">\n";
		for(Map.Entry<Person, String> entry: person.getRelations().entrySet()){
			if(entry.getValue().equals(relationType)){
				result +=
						"<a href ='../id" + entry.getKey().getPersonId() + "'>" +
									 entry.getKey().getFirstName() +"</a>\n" +
						"<br>\n";
			}
		}
		if(result.equals("<td valign=\"top\">\n")) return result + " (empty)\n </td>\n";
		return result + "</td>\n";
}
	
	//returns a "html line" of relation types
	public static String additionalColumnType(Person person){
		String result = "";
		for(String relationType: person.getPersonCreatedRelationTypes()){
			result += "<td>" + relationType + "</td>\n";
		}
		return result;
	}
	
	public static String additionalRelations(Person person, String personCreatedRelationType){
		String result = "";
		//TODO
		for(String relationType: person.getPersonCreatedRelationTypes()){
				
		}			
		return "";
	}
*/	
}
