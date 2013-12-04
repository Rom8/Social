package persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the ACCESS_CIRCLE database table.
 * 
 */
@Entity
@Table(name="ACCESS_CIRCLE")
@Access(AccessType.PROPERTY)
@NamedQueries({
	@NamedQuery(name="AccessCircle.findAll", 
				query="SELECT a FROM AccessCircle a"),
	@NamedQuery(name="AccessCircle.findByNameAndPerson",
				query="SELECT a FROM AccessCircle a WHERE a.name = :name AND a.person = :person")
})
public class AccessCircle implements Serializable {
	private static final long serialVersionUID = 12L;
	private long circleId;
	@Access(AccessType.FIELD)
	private byte enabled;
	private String name;
	private Person person;
	private Set<Person> persons;

	public AccessCircle() {
	}

	public AccessCircle(String name) {
		this.name = name;
		this.setEnabled(true);
	}

	@Id
	@SequenceGenerator(name="Circle_Gen", sequenceName="access_circle_seq", allocationSize=1)
	@GeneratedValue(generator="Circle_Gen")
	@Column(name="CIRCLE_ID")
	public long getCircleId() {
		return this.circleId;
	}

	public void setCircleId(long circleId) {
		this.circleId = circleId;
	}
	
	public boolean getEnabled(){
		return enabled == 1;
	}
	
	public void setEnabled(boolean enabled){
		if(enabled)
			this.enabled = 1;
		else
			this.enabled = 0;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="PERSON_ID")
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	//uni-directional many-to-many association to Person
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="RELATION"
		, joinColumns={ @JoinColumn(name="ACCESS_CIRCLE_ID") }
		, inverseJoinColumns={ @JoinColumn(name="TARGET_PERSON_ID") }
		)
	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	
	public boolean addPerson(Person person){
		if(persons.contains(person))
			return false;
		else{
			persons.add(person);
			return true;
		}
	}
	
//	public boolean movePersonToCircle(Person person, AccessCircle circle){
//		return this.persons.remove(person) && circle.addPerson(person);
//	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AccessCircle)) {
			return false;
		}
		AccessCircle circle = (AccessCircle) obj;
		return this.circleId == circle.circleId;
	}
	
	@Override
	public int hashCode() {
		return (int) this.circleId
				+ this.name.hashCode();
	}

}