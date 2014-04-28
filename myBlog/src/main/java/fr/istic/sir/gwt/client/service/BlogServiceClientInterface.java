package fr.istic.sir.gwt.client.service;

import fr.istic.sir.gwt.client.model.BlogCategory;
import fr.istic.sir.gwt.client.model.BlogUser;

public interface BlogServiceClientInterface {
	void signUp(String id, String pw, String fn, String ln);
	void logIn(String id, String pw);
	void categoriesList();
	void createCategory(String category);
	void blogList(String category);
	void createBlog(String title, String content, BlogUser user, BlogCategory category);
}
