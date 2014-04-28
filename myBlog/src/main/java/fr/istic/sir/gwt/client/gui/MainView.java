package fr.istic.sir.gwt.client.gui;


import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.istic.sir.gwt.client.event.BlogListEvent;
import fr.istic.sir.gwt.client.event.CallbackEvent;
import fr.istic.sir.gwt.client.event.CategoriesEvent;
import fr.istic.sir.gwt.client.event.CategoryCreatedEvent;
import fr.istic.sir.gwt.client.event.CreatedBlogEvent;
import fr.istic.sir.gwt.client.event.LoginEvent;
import fr.istic.sir.gwt.client.event.SignupEvent;
import fr.istic.sir.gwt.client.model.BlogCategory;
import fr.istic.sir.gwt.client.model.BlogUser;
import fr.istic.sir.gwt.client.model.MyBlog;
import fr.istic.sir.gwt.client.service.BlogServiceClientImpl;

public class MainView extends Composite{
	
	private BlogServiceClientImpl listener;
	private VerticalPanel vPanel = new VerticalPanel();
	private BasePage currentPage;
	private BlogUser me;
	private List<BlogCategory> categories;
	private BlogCategory currentCategory;
	private List<MyBlog> blogList;
	
	public MainView(BlogServiceClientImpl listener){
		this.listener = listener;
		initWidget(this.vPanel);
		this.vPanel.setWidth("100%");
		
		openSignUpPage();
	}
	
	public void handleCallbackEvent(CallbackEvent event){
		// Log in
		if(event instanceof LoginEvent){
			LoginEvent result = (LoginEvent) event;
			this.me = result.getUser();
			if(result.isOk()){
				// TODO !!!!
				this.listener.categoriesList();
			}else{
				openSignUpPage();
			}
		} 
		// Sign up 
		else if(event instanceof SignupEvent){
			this.me = ((SignupEvent) event).getUser();
			this.listener.categoriesList();
		}
		// Categories
		else if(event instanceof CategoriesEvent){
			CategoriesEvent result = (CategoriesEvent) event;
			this.categories = result.getCategories();
			if(this.categories.size() == 0){
				openCreateCategoryPage();
			}else{
				this.currentCategory = this.categories.get(0);
				openBlogFrame();
			}
		}
		// Category create
		else if(event instanceof CategoryCreatedEvent){
			CategoryCreatedEvent result = (CategoryCreatedEvent) event;
			this.currentCategory = result.getBlogCategory();
			this.categories.add(currentCategory);
			openBlogFrame();
		}
		// Blog List
		else if(event instanceof BlogListEvent){
			BlogListEvent result = (BlogListEvent) event;
			this.blogList = result.getBlogList();
			openBlogFrame();
		}
		// Blog List
		else if(event instanceof CreatedBlogEvent){
			this.listener.blogList(this.getCurrentCategory().getCategory());
		}
	}

	public void openSignUpPage(){
		openPage(new SignUpPage(this));
	}
	
	public void openLoginPage(){
		openPage(new LoginPage(this));
	}
	
	public void openBlogFrame(){
		openPage(new BlogFrame(this));
	}
	
	public void openCreateCategoryPage(){
		openPage(new CreateCategoryPage(this));
	}
	
	public void openBlogWritePage(){
		openPage(new BlogWritePage(this));
	}
	
	////////////////////////////////////////////////
	private void openPage(BasePage page){
		this.vPanel.clear();
		currentPage = page;
		this.vPanel.add(currentPage);
		this.vPanel.setCellHorizontalAlignment(currentPage, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	//////// getter //////////////////////////
	
	public BlogServiceClientImpl getListener() {
		return this.listener;
	}
	
	public BlogUser getBlogUser(){
		return this.me;
	}

	public BasePage getCurrentPage() {
		return currentPage;
	}

	public BlogUser getMe() {
		return me;
	}

	public List<BlogCategory> getCategories() {
		return categories;
	}

	public BlogCategory getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(BlogCategory currentCategory) {
		this.currentCategory = currentCategory;
	}

	public List<MyBlog> getBlogList() {
		return blogList;
	}
}
