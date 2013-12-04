package testservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import controller.Links;
import persistence.entitymanagers.EntityService;
import persistence.model.Person;
import persistence.model.RelationType;

/**
 * Servlet implementation class ForTest
 */
@WebServlet("/ForTest")
public class ForTest extends HttpServlet {
	private static final long serialVersionUID = 2L;
    @EJB EntityService es;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>ForTest</title>");
		out.println("</head>");		
		out.println("<body>");
//		out.println(es.getSepTest(1).getId() + " " + es.getSepTest(1).getContent());
//		out.println(es.getSepOneTest(1).getId() + " " + es.getSepOneTest(1).getContentOne());
//		es.createNewPerson("email4", "password4", "firstName4");
//		out.println(es.getPersonById(1).getFirstName() + "<br>");
//		out.println(es.getPersonById(2).getFirstName() + "<br>");
//		out.println(es.getPersonById(3).getFirstName() + "<br>");
//		out.println(es.getPersonById(24).getFirstName());
//		Set<String> nickNames = es.getEmployeeNickNames(es.getEmployeeById(1));
//		es.getEmployeeById(1).addNickName("superKostya");
//		List<VacationEntry> list = new ArrayList<>(es.getEmployeeById(1).getVacationBooking());
//		out.println(list.toString());
//		System.out.println(es.getEmployeeById(1).getVacationBooking() == null ? "it's null" : "not null");
//		System.out.println(es.getEmployeeById(1).getVacationBooking().getClass().getName());
//		out.println(es.getEmployeeById(1));
//		es.addNewNickNameToEmp("superMister", es.getEmployeeById(1));

//		for(String nk: es.getEmployeeById(1).getNickNames()){
//			out.println(nk + "<br>");
//		}
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//		for(VacationEntry s: es.getEmployeeById(1).getVacationBooking()){
//			out.println(dateFormat.format(s.getStartDate().getTime()) + " for " + 
//						s.getDaysTaken() + " days");
//		}
//		out.println("<br>" + es.getEmployeeById(1).getPhoneNumbers().get(PhoneType.Mobile));
		
		
		
//		es.addNewRelation(es.getPersonById(24), es.getPersonById(1), RelationType.IFollow);
		// works - puts into db
//		System.out.println("puts into db");
//		for(Person p: es.getPersonById(24).getRelations().keySet()){
//			out.println("<br>keySet      " + p.getPersonId() + " " + p.getFirstName());
//		}
//		for(Object p: es.getPersonById(24).getRelations().keySet()){
//			out.println("<br>p instanceof Person: ");
//			out.println(p instanceof Person);
//		}
//		
//		for(RelationType rt: es.getPersonById(24).getRelations().values()){
//			out.println("<br>values      " + rt);
//		}
//		for(Map.Entry<Person, RelationType> st: es.getPersonById(24).getRelations().entrySet()){ 
//			out.println("<br>Map.Entry Key:   " + st.getKey().getFirstName());
//			out.println("<br>Map.Entry Value: " + st.getValue().name());
//		}
//		
//		for(Map.Entry<Person, RelationType> entry: es.getPersonById(24).getRelations().entrySet()){ 
//			out.println(entry.getKey().getFirstName() + " " + entry.getValue());
//		}
//		
//		out.println("<br>" + request.getRequestURL().toString());
//		String val = request.getParameter("personId");
//		if(request.getAttributeNames().hasMoreElements())
//			for(Enumeration<String> names = request.getAttributeNames();names.hasMoreElements();){
//				String tempName = names.nextElement();
//				out.println("<br>" + tempName + " " + request.getParameter(tempName));
//			}
//		else out.println("Empty!!!");
//		out.println("<br> His id is: " + val);
		
		// works - returns keys and values
//		String s = "non";
//		Object per;
//		RelationType r = es.getPersonById(3).getRelations().get(
//				per = es.getPersonById(1));// != null 
////				? s = "it's not null" 
////				: "yes, null");
//		System.out.println(per instanceof Person);
//		System.out.println(r == null);
//		System.out.println(es.getPersonById(24).getRelations().size());
		
//		out.println("<br>Get		   " + es.getPersonById(24).getRelations().get(es.getPersonById(1)));
//		out.println("<br>Size          " + es.getPersonById(24).getRelations().size());
//		out.println("<br>isEmpty       " + es.getPersonById(24).getRelations().isEmpty());
//		out.println("<br>containsKey   " + es.getPersonById(24).getRelations().containsKey(es.getPersonById(1)));
//		out.println("<br>containsValue " + es.getPersonById(24).getRelations().containsValue(RelationType.IFollow));
//		out.println("<br>keySet.size   " + es.getPersonById(24).getRelations().keySet().size());
//		out.println("<br>values.size   " + es.getPersonById(24).getRelations().values().size());
		
//		es.addNewNote(es.getPersonById(24), "Test note 2");
		
//		HttpSession session = request.getSession(false);
//		out.println( ( (Person) session.getAttribute(Links.person)).getLastName() );
		
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
