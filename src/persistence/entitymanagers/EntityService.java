package persistence.entitymanagers;

import javax.ejb.Local;

import persistence.model.AccessCircle;
import persistence.model.Person;

@Local
public interface EntityService {
	void createNewPerson(String email, String password, String firstName);
	Person getPersonById(long personId);
	Person singPersonIn(String email, String password);
	boolean isEmailExist(String email);
	AccessCircle getCircle(String name, Person person);
}
