package fr.istic.sir.gwt.client.service;


import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import fr.istic.sir.gwt.client.event.CallbackEvent;
import fr.istic.sir.gwt.client.gui.MainView;
import fr.istic.sir.gwt.client.model.BlogCategory;
import fr.istic.sir.gwt.client.model.BlogUser;

public class BlogServiceClientImpl implements BlogServiceClientInterface{
	
	private BlogServiceAsync service;
	private MainView mainview;
	
	public BlogServiceClientImpl(String url){
		this.service = GWT.create(BlogService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		this.mainview = new MainView(this);
	}
	
	public MainView getMainGUI(){
		return this.mainview;
	}

	public void signUp(String id, String pw, String fn, String ln) {
		service.signUp(id, pw, fn, ln, new DefaultCallback());
	}

	public void logIn(String id, String pw) {
		service.logIn(id, pw, new DefaultCallback());
	}
	
	public void categoriesList() {
		service.categoriesList(new DefaultCallback());
	}
	
	public void createCategory(String category) {
		service.createCategory(category, new DefaultCallback());
	}
	
	public void blogList(String category) {
		service.blogList(category, new DefaultCallback());
	}
	
	public void createBlog(String title, String content, BlogUser user, BlogCategory category) {
		service.createBlog(title, content, user, category, new DefaultCallback());
	}
	
	private class DefaultCallback implements AsyncCallback<CallbackEvent>{
		public void onFailure(Throwable caught) {
			// TODO popup!!!
			System.out.println("Error!!! " + caught.getMessage());
		}
		public void onSuccess(CallbackEvent result) {
			mainview.handleCallbackEvent(result);
		}
	}

	

	

	



}
