package persistence.entitymanagers;

import javax.ejb.Local;

@Local
public interface GenericManager<T> {
	void setEntity(T t);
	T getEntity();
	void save();
	void cancel();
}
