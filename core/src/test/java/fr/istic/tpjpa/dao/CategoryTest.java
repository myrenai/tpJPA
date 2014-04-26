package fr.istic.tpjpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.tpjpa.Utils;

public class CategoryTest extends BaseTest{
	
	@Test
	public void findCategory(){
		Category c1 = Utils.makeMockCategory("JPA");
		categoryDao.saveCategory(em, c1);
		
		Category c = categoryDao.findCategory(em, "JPA");
		assertNotNull(c);
	}
	
	@Test
	public void listCategory(){
		categoryDao.saveCategory(em, Utils.makeMockCategory("JPA"));
		categoryDao.saveCategory(em, Utils.makeMockCategory("JAVA"));
		categoryDao.saveCategory(em, Utils.makeMockCategory("GWT"));
		categoryDao.saveCategory(em, Utils.makeMockCategory("NOSQL"));
		
		List<Category> clist = categoryDao.listCategory(em);
		assertEquals(4, clist.size());		
	}
	
	@Test
	public void updateCategory(){
		categoryDao.saveCategory(em, Utils.makeMockCategory("JPA"));
		Category c = categoryDao.findCategory(em, "JPA");
		c.setCategory("ORM");
		
		categoryDao.updateCategory(em, c);
		
		Category c2 = categoryDao.findCategory(em, "ORM");
		assertNotNull(c2);
	}
}
