package persistence.entitymanagers;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Session Bean implementation class GenericManagerBean
 */
@Stateful
@EJB(name="GenericManager", beanInterface=GenericManager.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GenericManagerBean<T> implements GenericManager<T> {
	@PersistenceContext(unitName = "Social", type = PersistenceContextType.EXTENDED)
	EntityManager entityManager;
	T t;

    public GenericManagerBean() {
    }

	public void setEntity(T t) {
		this.t = t;
	}

	public T getEntity() {
		return t;
	}

	@Remove
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save() {
		entityManager.merge(t);
		System.out.println(t.toString() + " has been merged.");
	}

	@Remove
	public void cancel(){
	}
    
}
