package persistence.model;

import helpers.HttpMethodType;
import helpers.JSPHelper;

public enum RelationType {
	FRIENDS{
		@Override
		public String nameLine(){
			return "My friends";
		}

		@Override
		public String lineForPerson(Person person) {
			return person.getFirstName() + " is your friend";
		}

		@Override
		public String getButtons(Person person, String URI) {
			return JSPHelper
					.getButton(HttpMethodType.POST, "Keep as follower", 
							"keepAsFollower", String.valueOf(person.getPersonId()),
							"oldURI", URI
							);
		}
	},
	
	REQUESTED{
		@Override
		public String nameLine(){
			return "Friendship requests to me";
		}

		@Override
		public String lineForPerson(Person person) {
			return person.getFirstName() + " sent you a friendship request";
		}

		@Override
		public String getButtons(Person person, String URI) {
			return  JSPHelper
					.getButton(HttpMethodType.POST, "Confirm friend request", 
							"confirmFriendRequest", String.valueOf(person.getPersonId()),
							"oldURI", URI
							)
				+ "\n"
				+ JSPHelper
					.getButton(HttpMethodType.POST, "Keep as follower", 
							"keepAsFollower", String.valueOf(person.getPersonId()),
							"oldURI", URI
							);
		}
	},
	
	MYREQUESTS{
		@Override
		public String nameLine(){
			return "I request friendship";
		}

		@Override
		public String lineForPerson(Person person) {
			return person.getFirstName() + " has not yet considered your friendship request";
		}

		@Override
		public String getButtons(Person person, String URI) {
			return JSPHelper
					.getButton(HttpMethodType.POST, "Cancel friend request",
							"cancelFriendRequest", String.valueOf(person.getPersonId()),
							"oldURI", URI
							);
		}
	},
	
	FOLLOWERS{
		@Override
		public String nameLine(){
			return "My followers";
		}

		@Override
		public String lineForPerson(Person person) {
			return person.getFirstName() + " is following you";
		}

		@Override
		public String getButtons(Person person, String URI) {
			return JSPHelper
					.getButton(HttpMethodType.POST, "Confirm friend request", 
							"confirmFriendRequest", String.valueOf(person.getPersonId()),
							"oldURI", URI
							);
		}
	},
	
	IFOLLOW{
		@Override
		public String nameLine(){
			return "I follow";
		}

		@Override
		public String lineForPerson(Person person) {
			return "You are following " + person.getFirstName();
		}

		@Override
		public String getButtons(Person person, String URI) {
			return JSPHelper
					.getButton(HttpMethodType.POST, "Unfollow",
							"unfollow", String.valueOf(person.getPersonId()),
							"oldURI", URI
							);
		}
	},
	
	LOVER{
		@Override
		public String nameLine(){
			return "My love";
		}

		@Override
		public String lineForPerson(Person person) {
			return "You are in relationship with " + person.getFirstName();
		}

		@Override
		public String getButtons(Person person, String URI) {
			return JSPHelper
					.getButton(HttpMethodType.POST, "Divorce",
							"divorce", String.valueOf(person.getPersonId()),
							"oldURI", URI
							);
		}
	},
	
	BANNED{
		@Override
		public String nameLine(){
			return "People, banned by me";
		}

		@Override
		public String lineForPerson(Person person) {
			return person.getFirstName() + " is in my BAN list";
		}

		@Override
		public String getButtons(Person person, String URI) {
			return JSPHelper
					.getButton(HttpMethodType.POST, "Unban",
							"unban", String.valueOf(person.getPersonId()),
							"oldURI", URI
							);
		}
	},
	
	IAMINBAN{
		@Override
		public String nameLine(){
			return "I am in ban";
		}

		@Override
		public String lineForPerson(Person person) {
			return person.getFirstName() + " hided info";
		}

		@Override
		public String getButtons(Person person, String URI) {
			// TODO Auto-generated method stub
			return "";
		}
	},
	
	INTERNET{
		@Override
		public String nameLine(){
			return "Please, <a href='signin.jsp'> sign in </a>";
		}

		@Override
		public String lineForPerson(Person person) {
			return "Please, <a href='signin.jsp'> sign in </a>";
		}

		@Override
		public String getButtons(Person person, String URI) {
			// TODO Auto-generated method stub
			return "";
		}
	},
	
	MYPAGE{
		@Override
		public String nameLine(){
			return "My page";
		}

		@Override
		public String lineForPerson(Person person) {
			return "My page";
		}

		@Override
		public String getButtons(Person person, String URI) {
			// TODO Auto-generated method stub
			return "";
		}
	},
	
	OTHERS{
		@Override
		public String nameLine() {
			return "Not in your circles";		//TODO not good idea
		}

		@Override
		public String lineForPerson(Person person) {
			return person.getFirstName() + " is not in your circles";
		}

		@Override
		public String getButtons(Person person, String URI) {
			return JSPHelper.getButton(HttpMethodType.POST, "Send Friend Request",
					"friendRequest", String.valueOf(person.getPersonId()),
					"oldURI", URI
					)
				+ "\n" +
					JSPHelper.getButton(HttpMethodType.POST, "Follow",
					"follow", String.valueOf(person.getPersonId()),
					"oldURI", URI
					);
		}
	};
	
	public abstract String nameLine();
	public abstract String lineForPerson(Person person);
	public abstract String getButtons(Person person, String URI);
	
}
