package fr.istic.sir.gwt.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
import fr.istic.sir.gwt.client.service.BlogService;
import fr.istic.sir.tpjpa.domain.Blog;
import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.sir.tpjpa.domain.User;
import fr.istic.sir.tpjpa.service.CategoryService;
import fr.istic.sir.tpjpa.service.UserService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class BlogServiceImpl extends RemoteServiceServlet implements BlogService {
	
	UserService userService  = UserService.singleton;
	CategoryService categoryService = CategoryService.singleton;
	fr.istic.sir.tpjpa.service.BlogService blogService = fr.istic.sir.tpjpa.service.BlogService.singleton;
	
	
	public CallbackEvent signUp(String userId, String userPw, String firstName, String lastName) {
		User user = Utils.makeMockUser(userId, userPw, firstName, lastName);
		userService.createUser(user);
		SignupEvent callbackEvent = new SignupEvent();
		callbackEvent.setUser(Utils.convertBlogUser(user));
		return callbackEvent;
	}

	public CallbackEvent logIn(String userId, String userPw) {
		LoginEvent callbackEvent = new LoginEvent();
		User user = userService.findUser(userId);
		if(user != null && user.getUserPw().equals(userPw)){
			callbackEvent.setOk(true);
			callbackEvent.setUser(Utils.convertBlogUser(user));
			return callbackEvent;
		}else{
			callbackEvent.setOk(false);
			return callbackEvent;
		}
	}

	public CallbackEvent categoriesList() {
		List<Category> categories = categoryService.listCategory();
		List<BlogCategory> blogCategories = new ArrayList<BlogCategory>();
		for(Category c : categories){
			BlogCategory bc = Utils.convertBlogCategory(c);
			blogCategories.add(bc);
		}
		CategoriesEvent callbackEvent = new CategoriesEvent();
		callbackEvent.setCategories(blogCategories);
		return callbackEvent;
	}

	public CallbackEvent createCategory(String categoryStr) {
		Category category = new Category();
		category.setCategory(categoryStr);
		categoryService.createCategory(category);
		
		CategoryCreatedEvent callbackEvent = new CategoryCreatedEvent();
		callbackEvent.setBlogCategory(Utils.convertBlogCategory(category));
		return callbackEvent;
	}

	public CallbackEvent blogList(String category) {
		Category c = new Category();
		c.setCategory(category);
		List<Blog> blogList = blogService.listBlogsByCategory(c);
		List<MyBlog> myBlogList = new ArrayList<MyBlog>();
		
		BlogListEvent callbackEvent = new BlogListEvent();
		for(Blog b : blogList){
			myBlogList.add(Utils.convertMyBlog(b));
		}
		callbackEvent.setBlogList(myBlogList);
		return callbackEvent;
	}

	public CallbackEvent createBlog(String title, String content, BlogUser user, BlogCategory category) {
		Blog blog = Blog.build()
				.setTitle(title)
				.setContent(content)
				.setCategory(Utils.convertBlogCategory(category))
				.setUser(Utils.convertBlogUser(user));
		// TODO find obj and merge
		blogService.createBlog(blog);
		return new CreatedBlogEvent();
	}
}
