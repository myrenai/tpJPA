package fr.istic.tpjpa.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fr.istic.sir.tpjpa.domain.Blog;
import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.sir.tpjpa.domain.User;
import fr.istic.tpjpa.Utils;

public class BlogTest extends BaseTest{
	
	
	@Test
	public void saveBlog(){
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		Blog blog = Utils.makeMockBlog(user, "test");
		blogDao.saveBlog(em, blog);
		
		org.junit.Assert.assertEquals(1, blogDao.countBlog(em));
	}
	
	@Test
	public void saveBlogWithCategory(){
		Category category = Utils.makeMockCategory("JPA");
		categoryDao.saveCategory(em, category);
		
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog2"));
	}
	
	@Test
	public void listBlogsByUser(){
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		Category category = Utils.makeMockCategory("JPA");
		categoryDao.saveCategory(em, category);
		
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog2"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog3"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog4"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog5"));
		
		List<Blog> blogs = blogDao.getBlogsByUser(em, user);
		assertEquals(5, blogs.size());
		
	}
	
	@Test
	public void cascadeTest(){
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		Category category = Utils.makeMockCategory("JPA");
		categoryDao.saveCategory(em, category);
		
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog2"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog3"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog4"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog5"));
		
		List<Blog> blogs = blogDao.getBlogsByUser(em, user);
		assertEquals(5, blogs.size());
		
		userDao.deleteUser(em, user);
		assertEquals(0, blogDao.countBlog(em));
	}
	
	@Test
	public void listByCategory(){
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		Category category = Utils.makeMockCategory("JPA");
		categoryDao.saveCategory(em, category);
		
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog2"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog3"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog4"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog5"));
		
		List<Blog> blogs = blogDao.getBlogsByCategory(em, category);
		assertEquals(5, blogs.size());
		
	}
	
	@Test
	public void listByTitle(){
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		Category category = Utils.makeMockCategory("JPA");
		categoryDao.saveCategory(em, category);
		
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog1", "content1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog2", "content1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog3", "content1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog4", "content1"));
		blogDao.saveBlog(em, Utils.makeMockBlog(user, category, "blog5", "content1"));
		
		List<Blog> blogs = blogDao.findBlogsByTitle(em, "blog");
		assertEquals(5, blogs.size());
	}

}
