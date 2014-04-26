package fr.istic.tpjpa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.sir.tpjpa.service.BlogService;
import fr.istic.sir.tpjpa.service.CategoryService;
import fr.istic.sir.tpjpa.service.UserService;
import fr.istic.tpjpa.Utils;

public class CategoryServiceTest {
	BlogService blogService = BlogService.singleton;
	UserService uservice = UserService.singleton;
	CategoryService categoryService = CategoryService.singleton;
	
	
	@Before
	public void setup(){
		categoryService.createCategory(Utils.makeMockCategory("JPA"));
		categoryService.createCategory(Utils.makeMockCategory("ORM"));
		categoryService.createCategory(Utils.makeMockCategory("JAVA"));
		categoryService.createCategory(Utils.makeMockCategory("SIR"));
		categoryService.createCategory(Utils.makeMockCategory("GWT"));
		assertEquals(5, categoryService.countCategory());
	}
	
	@Test
	public void listCategories(){
		List<Category> categories = categoryService.listCategory();
		assertEquals(5, categories.size());
	}
	
	@Test
	public void deleteCategory(){
		List<Category> categories = categoryService.listCategory();
		assertEquals(5, categories.size());
		categoryService.deleteCategory(categories.get(0));
		assertEquals(4, categoryService.countCategory());
	}
	
	@Test
	public void updateCategory(){
		List<Category> categories = categoryService.listCategory();
		assertEquals(5, categories.size());
		Category c = categories.get(0);
		c.setCategory("NEW CATEGORY");
		categoryService.updateCategory(c);
		Category c2 = categoryService.findCategory("NEW CATEGORY");
		assertEquals("NEW CATEGORY", c2.getCategory());
	}
	
}
