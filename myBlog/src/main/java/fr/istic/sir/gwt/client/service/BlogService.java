package fr.istic.sir.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.istic.sir.gwt.client.event.CallbackEvent;
import fr.istic.sir.gwt.client.model.BlogCategory;
import fr.istic.sir.gwt.client.model.BlogUser;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("main")
public interface BlogService extends RemoteService {
	CallbackEvent signUp(String id, String pw, String fn, String ln);
	CallbackEvent logIn(String id, String pw);
	CallbackEvent categoriesList();
	CallbackEvent createCategory(String category);
	CallbackEvent blogList(String category);
	CallbackEvent createBlog(String title, String content, BlogUser user, BlogCategory category);
}
