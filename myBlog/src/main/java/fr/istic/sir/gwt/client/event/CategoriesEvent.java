package fr.istic.sir.gwt.client.event;

import java.util.List;

import fr.istic.sir.gwt.client.model.BlogCategory;

public class CategoriesEvent extends CallbackEvent{
	
	private List<BlogCategory> categories;

	public List<BlogCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<BlogCategory> categories) {
		this.categories = categories;
	}

}
