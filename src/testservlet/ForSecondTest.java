package testservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
import controller.Helper;

/**
 * Servlet implementation class ForSecondTest
 */
@WebServlet("/ForSecondTest")
@EJBs({
	@EJB(name="GenericManager", beanInterface=GenericManager.class)
})
public class ForSecondTest extends HttpServlet {
	private static final long serialVersionUID = 11L;
	@EJB EntityService es;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>ForSecondTest</title>");
		out.println("</head>");		
		out.println("<body>");
//		
//		try {
//			InitialContext ic = new InitialContext();
//			StatefulExample stateEx = (StatefulExample) ic.lookup("java:comp/env/testBean");
//			stateEx.addLine("line 1a");
//			stateEx.addLine("line 2a");
//			for(String line: stateEx.getlist()){
//				out.println(line);
//			}
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
		
//		HttpSession session = request.getSession(false);
//		
//		try {
//			InitialContext initialContext = new InitialContext();
//			PersonManager personManager = (PersonManager) 
//					initialContext.lookup("java:comp/env/PersonManager");
//			personManager.setPerson( (Person) session.getAttribute(Links.person) );
//			personManager.getPerson().setLastName("Lyashenko");
//			personManager.finished();
//			out.println("she has now a last name");
//			
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} 
		
		
//		try {
//			InitialContext initialContext = new InitialContext();
//			PersonManager personManager = (PersonManager) 
//					initialContext.lookup("java:comp/env/PersonManager");
//			personManager.setPerson(24);
//			personManager.getPerson().addNote(new Note("test note " + new Date()));
//			personManager.finished();
//			initialContext.close();
//			out.println("she has now a brand-new note");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} finally {
//			
//		}
		
//		HttpSession session = request.getSession(false);
//			try {
//				InitialContext initialContext = new InitialContext();
//				PersonManager personManager = (PersonManager) 
//						initialContext.lookup("java:comp/env/PersonManager");
//			personManager.setPerson(Long.valueOf(Helper.getSessionPersonId(session)));
//			personManager.setPerson();
//			personManager.getPerson().addNote(new Note("test note " + new Date()));
//			personManager.getPerson()
//							.getRelations()
//							.put(es.getPersonById(1), "TestType1");
//			personManager.getPerson().addAccessCircle(new AccessCircle("testCircle6"));
//			personManager.getPerson().addAccessCircle(new AccessCircle("testCircle7"));
//				personManager.finished();
//				initialContext.close();
//				out.println("nothing happend");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			} 			
		
//			try {
//				InitialContext initialContext = new InitialContext();
//				PersonManager personManager = (PersonManager) 
//						initialContext.lookup("java:comp/env/PersonManager");
//			personManager.setPerson(Long.valueOf(Helper.getSessionPersonId(session)));
//			personManager.setPerson();
//			personManager.getPerson().addNote(new Note("test note " + new Date()));
//			personManager.getPerson()
//							.getRelations()
//							.put(es.getPersonById(1), "TestType1");
//			personManager.getPerson().addAccessCircle(new AccessCircle("testCircle6"));
//			personManager.getPerson().addAccessCircle(new AccessCircle("testCircle7"));
//				personManager.finished();
//				initialContext.close();
//				out.println("nothing happend");
//			} catch (NamingException e) {
//				e.printStackTrace();
//			} 			
			
//		try {
//			InitialContext initialContext = new InitialContext();
//			AccessCircleManager mngr = 
//					(AccessCircleManager) initialContext.lookup("java:comp/env/AccessCircleManager");
//			mngr.setAccessCircle(53);
//			mngr.getAccessCircle().addPerson(es.getPersonById(3));
//			mngr.getAccessCircle().addPerson(es.getPersonById(41));
//			mngr.finished();
//			initialContext.close();
//			out.println("nothing happend");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} 		
		
//		try {
//			InitialContext initialContext = new InitialContext();
//			GenericManager<Person> mngr = 
//					(GenericManager<Person>) initialContext.lookup("java:comp/env/GenericManager");
//			mngr.setEntity(es.getPersonById(3));
//			mngr.getEntity().setLastName("Novoskolcev");
//			mngr.getEntity().setMiddleName("Andreevich");
//			mngr.finished();
//			initialContext.close();
//			out.println("nothing happend");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} 		
		
//		try {
//		InitialContext initialContext = new InitialContext();
//		GenericManager<Person> mngr = 
//				(GenericManager<Person>) initialContext.lookup("java:comp/env/GenericManager");
//		mngr.setEntity(es.getPersonById(3));
//		out.println(mngr.getEntity().getMiddleName());
//		mngr.cancel();
//		initialContext.close();
//		out.println("nothing happend");
//	} catch (NamingException e) {
//		e.printStackTrace();
//	} 	
		
		
//		GenericManager<Person> mng = AccessHelper.getGenericManager();
//		mng.setEntity(es.getPersonById(61));
//		Person p = mng.getEntity();
//		p.setMiddleName("Unknown2");
//		mng.save();
		
//		Person andrew = es.getPersonById(165);
//		
//		GenericManager<Person> mng = AccessHelper.getGenericManager();
//		mng.setEntity(es.getPersonById(166));
//		Person p = mng.getEntity();
//		p.addAccessCircle(new AccessCircle("Roommates")).addPerson(andrew);
//		mng.save();
		
		out.println("nothing");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
