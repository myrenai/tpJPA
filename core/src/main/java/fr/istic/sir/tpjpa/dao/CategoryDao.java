package fr.istic.sir.tpjpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.istic.sir.tpjpa.domain.Category;

public class CategoryDao {
	public static final CategoryDao singleton = new CategoryDao();
	private CategoryDao(){ }
	
	
	public void saveCategory(EntityManager em, Category c){
		em.persist(c);
	}
	
	/**
	 * Find a category with the category string
	 * @param category
	 * @return
	 */
	public Category findCategory(EntityManager em, String category){
		Query q = em.createQuery("select m from Category m where category = :category");
		q.setParameter("category", category);
		return (Category) q.getSingleResult();
	}
	
	
	public Category updateCategory(EntityManager em, Category c){
		return em.merge(c);
	}

	public void deleteCategory(EntityManager em, Category c){
		Category pc = em.find(Category.class, c.getId());		
		em.remove(pc);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> listCategory(EntityManager em ){
		Query q = em.createQuery("select m from Category m");
		
		List<Category> clist = new ArrayList<Category>();
		clist.addAll(q.getResultList());
		return clist;
	}
}
