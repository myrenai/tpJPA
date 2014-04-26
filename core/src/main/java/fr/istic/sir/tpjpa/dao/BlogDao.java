package fr.istic.sir.tpjpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.istic.sir.tpjpa.domain.Blog;
import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.sir.tpjpa.domain.User;

public class BlogDao {
	
	public static final BlogDao singleton = new BlogDao();
	private BlogDao(){ }
	
	public void saveBlog(EntityManager em, Blog blog){
		em.merge(blog);
	}
	
	public int countBlog(EntityManager em){
		Query q = em.createQuery("select count(m) from Blog m");
		int cnt = ((Long) q.getSingleResult()).intValue();
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public List<Blog> getBlogsByUser(EntityManager em, User user){
		Query q = em.createQuery("select m from Blog m where user_id =:user_id");
		q.setParameter("user_id", user.getId());
		List<Blog> b = new ArrayList<Blog> ();
		b.addAll(q.getResultList());
		return b;
	}
	
	@SuppressWarnings("unchecked")
	public List<Blog> getBlogsByCategory(EntityManager em, Category c){
		
		Query q = em.createQuery("select m from Blog m where category_id =:category_id");
		q.setParameter("category_id", c.getId());
		
		List<Blog> b = new ArrayList<Blog> ();
		b.addAll(q.getResultList());
		return b;
	}
	
	@SuppressWarnings("unchecked")
	public List<Blog> findBlogsByTitle(EntityManager em, String title){
		Query q = em.createQuery("select m from Blog m where title like :title");
		q.setParameter("title", "%" + title + "%");
		
		List<Blog> b = new ArrayList<Blog> ();
		b.addAll(q.getResultList());
		
		return b;
	}
	
}
