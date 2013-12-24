package persistence.entitymanagers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AccessHelper {
	
	//TODO handle exceptions!!!!
	
	public static <T> GenericManager<T> getGenericManager(){
		
		try {
			InitialContext initialContext = new InitialContext();
			@SuppressWarnings("unchecked")
			GenericManager<T> gm = 
					(GenericManager<T>) initialContext.lookup("java:comp/env/GenericManager");
			initialContext.close();
			return gm;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.err.println("Naming Exception in AccessHelper!");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static EntityService getEntityService(){
		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			EntityService es = (EntityService) initialContext.lookup("java:comp/env/EntityService");
			initialContext.close();
			return es;
		} catch (NamingException e) {
			System.err.println("Naming Exception in AccessHelper.getEntityService");
			e.printStackTrace();
			return null;
		}
	}
}
