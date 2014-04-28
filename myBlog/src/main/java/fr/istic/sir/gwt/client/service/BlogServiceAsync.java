package fr.istic.sir.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.istic.sir.gwt.client.event.CallbackEvent;
import fr.istic.sir.gwt.client.model.BlogCategory;
import fr.istic.sir.gwt.client.model.BlogUser;

public interface BlogServiceAsync {
	void signUp(String id, String pw, String fn, String ln, AsyncCallback<CallbackEvent> callback);
	void logIn(String id, String pw, AsyncCallback<CallbackEvent> callback);
	void categoriesList(AsyncCallback<CallbackEvent> callback);
	void createCategory(String category, AsyncCallback<CallbackEvent> callback);
	void blogList(String category, AsyncCallback<CallbackEvent> callback);
	void createBlog(String title, String content, BlogUser user, BlogCategory category, AsyncCallback<CallbackEvent> callback);
}
