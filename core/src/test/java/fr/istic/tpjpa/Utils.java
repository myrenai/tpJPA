package fr.istic.tpjpa;

import java.util.Calendar;

import fr.istic.sir.tpjpa.domain.Address;
import fr.istic.sir.tpjpa.domain.Blog;
import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.sir.tpjpa.domain.User;

public class Utils {
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
	
	public static Blog makeMockBlog(User user, String content){
		return Blog.build()
				.setUser(user)
				.setContent(content);
	}
	
	public static Blog makeMockBlog(User user, Category category, String content){
		return makeMockBlog(user, category, "title", content);
	}
	
	public static Blog makeMockBlog(User user, Category category, String title, String content){
		return Blog.build()
				.setUser(user)
				.setCategory(category)
				.setTitle(title)
				.setContent(content);
	}
	
	public static Category makeMockCategory(String category){

		return Category.build().setCategory(category);
	}
}
