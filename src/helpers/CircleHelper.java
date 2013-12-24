package helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistence.model.AccessCircle;
import persistence.model.Person;
import persistence.model.RelationType;

public class CircleHelper {

	private static String printPersonRef(Person p){
		return "<br><a href=\"/Social/id" + p.getPersonId() + "\">"
				+ p.getFirstName()
				+ "</a>";
	}
	
	public static String printCircle(AccessCircle circle, String URI){
		String result = "";
		List<Person> persons = new ArrayList<Person>(circle.getPersons());
		Collections.sort(persons);
		RelationType r = RelationType.valueOf(circle.getName());
		switch(r) {
			case FRIENDS:
				for(Person p: persons){
					result += printPersonRef(p)
							+ RelationType.FRIENDS.getButtons(p, URI);
				}
				break;
			case MYREQUESTS:
				for(Person p: persons){
					result += printPersonRef(p)
							+ RelationType.MYREQUESTS.getButtons(p, URI);
				}
				break;
			case REQUESTED:
				for(Person p: persons){
					result += printPersonRef(p)
							+ RelationType.REQUESTED.getButtons(p, URI);
				}
				break;
			case FOLLOWERS:
				for(Person p: persons){
					result += printPersonRef(p)
							+ RelationType.FOLLOWERS.getButtons(p, URI);
				}
				break;
			case IFOLLOW:
				for(Person p: persons){
					result += printPersonRef(p)
							+ RelationType.IFOLLOW.getButtons(p, URI);
				}
				break;
			case LOVER:
				for(Person p: persons){
					result += printPersonRef(p)
							+ RelationType.LOVER.getButtons(p, URI);
				//TODO add additional opportunities
				}
				break;
			case BANNED:
				for(Person p: persons){
					result += printPersonRef(p)
							+ RelationType.BANNED.getButtons(p, URI);
				}
				break;
			default:
				//TODO
				break;
		}
		return result;
	}
}
