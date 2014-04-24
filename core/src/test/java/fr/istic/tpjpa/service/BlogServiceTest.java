package fr.istic.tpjpa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.istic.tpjpa.Utils;
import fr.istic.tpjpa.domain.Blog;
import fr.istic.tpjpa.domain.Category;
import fr.istic.tpjpa.domain.User;
import fr.istic.tpjpa.service.BlogService;
import fr.istic.tpjpa.service.CategoryService;
import fr.istic.tpjpa.service.UserService;

public class BlogServiceTest {
	BlogService blogService = BlogService.singleton;
	UserService uservice = UserService.singleton;
	CategoryService categoryService = CategoryService.singleton;
	Category category;
	User user;
	
	@Before
	public void setup(){
		category = Utils.makeMockCategory("JPA");
		categoryService.createCategory(category);
		
		user = Utils.makeMockUser("Jiyoung", "Park", "myrenai");
		uservice.createUser(user);
		
		blogService.createBlog(Utils.makeMockBlog(user, category, "Hello my blog1", "Content blog1"));
		blogService.createBlog(Utils.makeMockBlog(user, category, "Hello my blog1", "Content blog2"));
		blogService.createBlog(Utils.makeMockBlog(user, category, "Hello my blog1", "Content blog3"));
		blogService.createBlog(Utils.makeMockBlog(user, category, "Hello my blog1", "Content blog4"));
		
		assertEquals(4, blogService.countBlog());
	}
	
	@Test
	public void blogsByCategory(){
		List<Blog> blogs = blogService.listBlogsByCategory(category);
		assertEquals(4, blogs.size());
	}
	
	@Test
	public void blogsByUser(){
		List<Blog> blogs = blogService.listBlogsByUser(user);
		assertEquals(4, blogs.size());
	}
	
	@Test
	public void searchByTitle(){
		List<Blog> blogs = blogService.listBlogsByTitle("Hello my blog");
		assertEquals(4, blogs.size());
	}
	
	@Test
	public void deleteBlog(){
		assertEquals(4, blogService.countBlog());
		List<Blog> blogs = blogService.listBlogsByTitle("Hello my blog");
		blogService.deleteBlog(blogs.get(0));
		assertEquals(3, blogService.countBlog());
	}
	
}
