package helpers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JSPHelper {
	
	private static final String BEGIN = 
			" <form action=\"/Social/FrontController\" method=\"";
	
	private static String end(String buttonValue){
		return
				"		<input type=\"submit\" 								" +
				"			   value=\"" + buttonValue +"\">				" +
				"	</form>													";
	}
	
	private static String nameAndValue(String name, String value){
		return  "<input type=\"hidden\"  " +
				"name=\"" + name + "\"		" +
				"value=\"" + value + "\" >	";
	}
	
	public static String getButton(
			HttpMethodType method, String buttonValue,
			String name1, String value1) {
		return BEGIN + method.toString() + "\">" + nameAndValue(name1, value1) + end(buttonValue);
	}
	
	public static String getButton(
			HttpMethodType method, String buttonValue,
			String name1, String value1, 
			String name2, String value2) {
		return 
				BEGIN + method.toString() + "\">" +
				nameAndValue(name1, value1) + 
				nameAndValue(name2, value2) +
				end(buttonValue);
	}
	
	public static String getButton(
			HttpMethodType method, String buttonValue,
			String name1, String value1,
			String name2, String value2,
			String... namesValues){
		if(namesValues.length % 2 != 0){
			return null;
		}else{
			String others = "";
			List<String> list = Arrays.asList(namesValues);
			for(Iterator<String> i = list.iterator(); i.hasNext(); ){
				others += nameAndValue(i.next(), i.next());
			}
			return
					BEGIN + method.toString() + "\">" +
					nameAndValue(name1, value1) +
					nameAndValue(name2, value2) +
					others +
					end(buttonValue);
		}
	}
	
}
