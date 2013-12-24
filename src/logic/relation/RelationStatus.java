package logic.relation;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import controller.Links;
import persistence.entitymanagers.AccessHelper;
import persistence.entitymanagers.EntityService;
import persistence.model.AccessCircle;
import persistence.model.Person;
import persistence.model.RelationType;

public class RelationStatus {
	private Person owner;
	private Person person;
	private RelationType relationType;
	private AccessCircle circle;
	private List<AccessCircle> optionalCircles;
	private EntityService es = AccessHelper.getEntityService();
	
	public RelationStatus(Person owner, Person person) throws NullPointerException {
		if(owner == null || person == null)
			throw new NullPointerException("any parameter cannot be null");
		constructorHelper(owner, person);
	}
	
	public RelationStatus(HttpSession session, Person person) 
			throws NullPointerException, IllegalStateException {
		if(session == null || person == null)
			throw new NullPointerException("any parameter cannot be null");
		if(person.getPersonId() == 0)
			throw new IllegalStateException("Illegal to use with new Person in parameter."
					+ " Person entity must be retrieved from database.");
		if(session.getAttribute(Links.PERSON_ID) == null){
			this.relationType = RelationType.INTERNET;
		}else{
			Person owner =  es.getPersonById((long) session.getAttribute(Links.PERSON_ID));
			constructorHelper(owner, person);
		}
	}
	
	public Person getOwner() {
		return owner;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public RelationType getRelationType() {
		return relationType;
	}
	
	public List<AccessCircle> getOptionalCircles() {
		return optionalCircles;
	}

	public RelationStatus reverse(){
		return new RelationStatus(person, owner);
	}
	
	private void constructorHelper(Person owner, Person person){
		this.owner = owner;
		this.person = person;
		optionalCircles = es.getCircles(owner, person);
		if(owner.equals(person)){
			this.relationType = RelationType.MYPAGE;
		}else if(optionalCircles.size() == 0){
			this.relationType = RelationType.OTHERS;
		}else{
			for(Iterator<AccessCircle> i = optionalCircles.iterator(); i.hasNext(); ){
				AccessCircle ac = i.next();
				if(ac.getName().matches("(FRIENDS)|(REQUESTED)|(MYREQUESTS)|"
						+ "(FOLLOWERS)|(IFOLLOW)|(LOVER)|(IAMINBAN)|(BANNED)")){
					this.circle = ac;
					this.relationType = RelationType.valueOf( this.circle.getName() );
					i.remove();
					break;
				}
			}			
		}
	}
}
