package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Helper {
	
 	public static void redirectSingedInToMyPage(HttpSession session,
			HttpServletResponse response) throws ServletException, IOException {
		if (session.getAttribute(Links.PERSON_ID) != null) {
			long personId = (Long) session.getAttribute(Links.PERSON_ID);
			String location = String.valueOf(personId);
			response.sendRedirect("id" + location);
		}
	}

}
