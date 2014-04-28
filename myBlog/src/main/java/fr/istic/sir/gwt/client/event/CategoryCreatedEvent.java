package fr.istic.sir.gwt.client.event;

import fr.istic.sir.gwt.client.model.BlogCategory;

public class CategoryCreatedEvent extends CallbackEvent{
	
	private BlogCategory blogCategory;

	public BlogCategory getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}
}
