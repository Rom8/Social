package persistence.entitymanagers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import controller.Links;
import persistence.model.AccessCircle;
import persistence.model.Person;
import persistence.model.RelationType;

/**
 * Session Bean implementation class EntityServiceBean
 */
@Stateless
@EJB(name="EntityService", beanInterface=EntityService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EntityServiceBean implements EntityService {

	@PersistenceContext(unitName="Social")
	EntityManager em;

	public EntityServiceBean() {
	}

	@PostConstruct
	public void init() {
		System.out.println("EntityService has been constructed.");
	}

	@PreDestroy
	public void shutdown() {
		System.out.println("EntityService has been shut");
	}
	
	public Person getPersonById(long personId) {
		return em.find(Person.class, personId);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void createNewPerson(String email, String password, String firstName){
		Person person = new Person(email, password, firstName);
		for(RelationType rt: RelationType.values())
			person.addAccessCircle(new AccessCircle(rt.toString()));
		em.persist(person);
		System.out.println("New user with name " + person.getFirstName() + " has been persisted.");
	}

	public Person singPersonIn(String email, String password) {
		try{
			Person person = em.createNamedQuery("Person.singIn", Person.class)
					.setParameter("email", email)
					.getSingleResult();
			return person.verifyPass(password) ? person : null;
		}catch(NoResultException noResult){
			return null;
		}
	}
	
	public Person getOwner(HttpSession session){
		long id = Long.parseLong( session.getAttribute(Links.PERSON_ID).toString() );
		return em.find(Person.class, id);
	}
	
	public List<Person> getMutualfriends(Person owner, Person person){
		Set<Person> ownerFriends = owner
				.getAccessCircleByName(RelationType.FRIENDS.toString()).getPersons();
		Set<Person> personFriends = person
				.getAccessCircleByName(RelationType.FRIENDS.toString()).getPersons();
		ownerFriends.retainAll(personFriends);
		List<Person> mutualFriends = new ArrayList<>(ownerFriends);
		Collections.sort(mutualFriends);
		return mutualFriends;
	}
	
	public boolean isEmailExist(String email){
		try{
			em.createNamedQuery("Person.isEmailExist", String.class)
					.setParameter("email", email)
					.getSingleResult();
			return true;
		}catch(NoResultException noResult){
			return false;
		}
	}

	public AccessCircle getCircle(String name, Person person){
		try{			
			return em.createNamedQuery("AccessCircle.findByNameAndPerson", AccessCircle.class)
					.setParameter("name", name)
					.setParameter("person", person)
					.getSingleResult();
		}catch(NoResultException noResult){
			return null;
		}
	}
	
	public AccessCircle getCircle(long circleID){
		return em.find(AccessCircle.class, circleID);
	}
	
	public List<AccessCircle> getCircles(Person owner, Person person){
		return em.createNamedQuery("AccessCircle.findAllCirclesWith", AccessCircle.class)
				.setParameter("owner", owner)
				.setParameter("person", person)
				.getResultList();
	}
	
}
