package fr.istic.sir.gwt.client.model;

import java.io.Serializable;

public class BlogCategory implements Serializable{
	private String category;
	
	public static BlogCategory build(){
		return new BlogCategory();
	}

	public String getCategory() {
		return category;
	}

	public BlogCategory setCategory(String category) {
		this.category = category;
		return this;
	}
	
}
