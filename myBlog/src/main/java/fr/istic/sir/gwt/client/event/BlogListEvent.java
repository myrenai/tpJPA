package fr.istic.sir.gwt.client.event;

import java.util.List;

import fr.istic.sir.gwt.client.model.MyBlog;

public class BlogListEvent extends CallbackEvent{
	
	private List<MyBlog> blogList;

	public List<MyBlog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<MyBlog> blogList) {
		this.blogList = blogList;
	}

}
