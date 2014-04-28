package fr.istic.sir.gwt.client.event;

import fr.istic.sir.gwt.client.model.BlogUser;

public class SignupEvent extends CallbackEvent{
	
	private BlogUser user;

	public BlogUser getUser() {
		return user;
	}

	public void setUser(BlogUser user) {
		this.user = user;
	}
	
}
