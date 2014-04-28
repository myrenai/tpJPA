package fr.istic.sir.gwt.client.model;

import java.io.Serializable;


public class MyBlog  implements Serializable{
	private String title;
	private String content;
	
	private BlogUser user;
	private BlogCategory category;
	
	public static MyBlog build(){
		return new MyBlog();
	}
	public String getTitle() {
		return title;
	}
	public MyBlog setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getContent() {
		return content;
	}
	public MyBlog setContent(String content) {
		this.content = content;
		return this;
	}
	public BlogUser getUser() {
		return user;
	}
	public MyBlog setUser(BlogUser user) {
		this.user = user;
		return this;
	}
	public BlogCategory getCategory() {
		return category;
	}
	public MyBlog setCategory(BlogCategory category) {
		this.category = category;
		return this;
	}
}
