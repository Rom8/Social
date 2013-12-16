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
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Keep as follower", 
										"keepAsFollower", String.valueOf(p.getPersonId()),
										"oldURI", URI
										);
				}
				break;
			case MYREQUESTS:
				for(Person p: persons){
					result += printPersonRef(p)
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Cancel friend request",
										"cancelFriendRequest", String.valueOf(p.getPersonId()),
										"oldURI", URI
										);
				}
				break;
			case REQUESTED:
				for(Person p: persons){
					result += printPersonRef(p)
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Confirm friend request", 
										"confirmFriendRequest", String.valueOf(p.getPersonId()),
										"oldURI", URI
										)
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Keep as follower", 
										"keepAsFollower", String.valueOf(p.getPersonId()),
										"oldURI", URI
										);
				}
				break;
			case FOLLOWERS:
				for(Person p: persons){
					result += printPersonRef(p)
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Confirm friend request", 
										"confirmFriendRequest", String.valueOf(p.getPersonId()),
										"oldURI", URI
										);
				}
				break;
			case IFOLLOW:
				for(Person p: persons){
					result += printPersonRef(p)
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Unfollow",
										"unfollow", String.valueOf(p.getPersonId()),
										"oldURI", URI
										);
				}
				break;
			case LOVER:
				for(Person p: persons){
					result += printPersonRef(p)
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Divorce",
										"divorce", String.valueOf(p.getPersonId()),
										"oldURI", URI
										);
				//TODO
				}
				break;
			case BANNED:
				for(Person p: persons){
					result += printPersonRef(p)
							+ JSPHelper
								.getButton(HttpMethodType.POST, "Unban",
										"unban", String.valueOf(p.getPersonId()),
										"oldURI", URI
										);
				}
				break;
			default:
				//TODO
				break;
		}
		return result;
	}
}
