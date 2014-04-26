package fr.istic.sir.gwt.server;

import java.util.Calendar;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.istic.sir.gwt.client.GreetingService;
import fr.istic.sir.gwt.shared.FieldVerifier;
import fr.istic.sir.tpjpa.domain.Address;
import fr.istic.sir.tpjpa.domain.User;
import fr.istic.sir.tpjpa.service.UserService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);
    
    UserService userService  = UserService.singleton;
    userService.createUser(
    		makeMockUser("Jiyoung","Park",input));

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }
  
  public static User makeMockUser(String firstName, String lastName, String userId){
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1980, 11, 9); 
		
		return User.build()
			.setUserId(userId)
			.setFirstName(firstName)
			.setLastName(lastName)
			.setBirthDate(birthDate.getTime())
			.setHomeAddress(makeMockAddr("home"))
			.setOfficeAddress(makeMockAddr("office"))
			.setUserPw("1787");
	}
	
	public static Address makeMockAddr(String type){
		return Address.build()
				.setCity(type + "City")
				.setStreet(type + "Street")
				.setCodePostal(type + "CodePostal")
				.setNation(type + "Nation");
		
	}

  /**
   * Escape an html string. Escaping data received from the client helps to
   * prevent cross-site script vulnerabilities.
   *
   * @param html the html string to escape
   * @return the escaped string
   */
  private String escapeHtml(String html) {
    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
}
