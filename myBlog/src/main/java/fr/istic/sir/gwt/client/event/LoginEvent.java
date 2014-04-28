package fr.istic.sir.gwt.client.event;

import fr.istic.sir.gwt.client.model.BlogUser;


public class LoginEvent extends CallbackEvent{
	private boolean ok;
	private BlogUser user;
	
	public boolean isOk() {
		return ok;
	}
	
	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public BlogUser getUser() {
		return user;
	}

	public void setUser(BlogUser user) {
		this.user = user;
	}
}
