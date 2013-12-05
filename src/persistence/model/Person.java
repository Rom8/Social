package persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import persistence.model.AccessCircle;;


/**
 * The persistent class for the PERSON database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Person.findAll",
				query="SELECT p FROM Person p"),
	@NamedQuery(name="Person.singIn",
				query="SELECT p FROM Person p WHERE p.email = :email"),
	@NamedQuery(name="Person.isEmailExist",
				query="SELECT p.email FROM Person p WHERE p.email = :email")
})
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private long personId;
	private Date birthday;
	private String email;
	private String firstName;
	private String hometown;
	private String lastName;
	private String middleName;
	private String password;
	private int hash;
	private int salt;
	private Set<AccessCircle> accessCircles = new HashSet<AccessCircle>();

	public Person() {
	}
	
	public Person(String email, String password, String firstName){
		this.email = email;
		createPass(password);
		this.firstName = firstName;
	}


	@Id
	@SequenceGenerator(name="Person_Gen", sequenceName="user_seq", allocationSize=1)
	@GeneratedValue(generator="Person_Gen")
	@Column(name="PERSON_ID")
	public long getPersonId() {
		return this.personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}


	@Temporal(TemporalType.DATE)
	@Past
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	@Column(unique=true)
	@NotNull
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="FIRST_NAME")
	@Size(min=1, max=30)
	@NotNull
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Size(min=1, max=40)
	public String getHometown() {
		return this.hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	
	@Column(name="LAST_NAME")
	@Size(min=1, max=40)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name="MIDDLE_NAME")
	@Size(min=1, max=40)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

//	@Size(min=6, max=22)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getHash() {
		return hash;
	}
	
	public void setHash(int hash) {
		this.hash = hash;
	}
	
	public int getSalt() {
		return salt;
	}
	
	public void setSalt(int salt) {
		this.salt = salt;
	}
	
	//bi-directional many-to-one association to AccessCircle
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<AccessCircle> getAccessCircles() {
		return this.accessCircles;
	}
	
	public AccessCircle getAccessCircleByName(String name){
		for(AccessCircle ac: this.accessCircles){
			if(ac.getName().equals(name))
				return ac;
		}
		return null;
	}

	public void setAccessCircles(Set<AccessCircle> accessCircles) {
		this.accessCircles = accessCircles;
	}

	public AccessCircle addAccessCircle(AccessCircle accessCircle) {
		getAccessCircles().add(accessCircle);
		accessCircle.setPerson(this);

		return accessCircle;
	}

	public AccessCircle removeAccessCircle(AccessCircle accessCircle) {
		getAccessCircles().remove(accessCircle);
		accessCircle.setPerson(null);

		return accessCircle;
	}
	
	private void createPass(String password){
		Random random = new Random();
		this.salt = random.nextInt();
		this.hash = (this.salt + password).hashCode();
	}
	
	public boolean verifyPass(String password){
		return (this.salt + password).hashCode() == this.hash;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Person)) {
			return false;
		}
		Person person = (Person) other;
		return this.personId == person.personId;
	}
	
	@Override
	public int hashCode() {
		return (int) this.personId;
	}
}