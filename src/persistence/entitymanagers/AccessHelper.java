package persistence.entitymanagers;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AccessHelper {
	
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
	
}
