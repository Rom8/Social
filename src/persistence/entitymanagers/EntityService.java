package persistence.entitymanagers;

import java.util.List;

import javax.ejb.Local;
import javax.servlet.http.HttpSession;

import persistence.model.AccessCircle;
import persistence.model.Person;

@Local
public interface EntityService {
	void createNewPerson(String email, String password, String firstName);
	Person getPersonById(long personId);
	Person singPersonIn(String email, String password);
	Person getOwner(HttpSession session);
	boolean isEmailExist(String email);
	AccessCircle getCircle(String name, Person person);
	AccessCircle getCircle(long circleID);
	List<AccessCircle> getCircles(Person owner, Person person);
}
