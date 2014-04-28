package fr.istic.sir.gwt.server;

import java.util.Calendar;

import fr.istic.sir.gwt.client.model.BlogCategory;
import fr.istic.sir.gwt.client.model.BlogUser;
import fr.istic.sir.gwt.client.model.MyBlog;
import fr.istic.sir.tpjpa.domain.Address;
import fr.istic.sir.tpjpa.domain.Blog;
import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.sir.tpjpa.domain.User;

public class Utils {
	
	public static User makeMockUser(String userId, String userPw){
		return User.build()
			.setUserId(userId)
			.setUserPw(userPw);
	}
	
	public static User makeMockUser(String userId, String userPw, String firstName, String lastName){
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1980, 11, 9); 
		
		return User.build()
			.setUserId(userId)
			.setUserPw(userPw)
			.setFirstName(firstName)
			.setLastName(lastName)
			.setBirthDate(birthDate.getTime())
			.setHomeAddress(makeMockAddr("home"))
			.setOfficeAddress(makeMockAddr("office"));
	}
	
	public static Address makeMockAddr(String type){
		return Address.build()
			.setCity(type + "City")
			.setStreet(type + "Street")
			.setCodePostal(type + "CodePostal")
			.setNation(type + "Nation");
	}
	
	public static BlogUser convertBlogUser(User user){
		return BlogUser.build()
			.setUserId(user.getUserId())
			.setUserPw(user.getUserPw())
			.setFirstName(user.getFirstName())
			.setLastName(user.getLastName());
	}
	
	public static BlogCategory convertBlogCategory(Category c){
		return BlogCategory.build().setCategory(c.getCategory());
	}
	
	public static MyBlog convertMyBlog(Blog b){
		return MyBlog.build()
			.setTitle(b.getTitle());
	}
	
	public static Category convertBlogCategory(BlogCategory c){
		return Category.build().setCategory(c.getCategory());
	}
	
	public static User convertBlogUser(BlogUser u){
		return User.build()
			.setUserId(u.getUserId());
	}
}
