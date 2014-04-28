package fr.istic.sir.gwt.client.model;

import java.io.Serializable;

public class BlogUser implements Serializable{
	
	private String userId;
	private String userPw;
	private String firstName;
	private String lastName;
	
	public static BlogUser build(){
		return new BlogUser();
	}
	
	public String getUserId() {
		return userId;
	}
	public BlogUser setUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getUserPw() {
		return userPw;
	}
	public BlogUser setUserPw(String userPw) {
		this.userPw = userPw;
		return this;
	}
	public String getFirstName() {
		return firstName;
	}
	public BlogUser setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public String getLastName() {
		return lastName;
	}
	public BlogUser setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

}
