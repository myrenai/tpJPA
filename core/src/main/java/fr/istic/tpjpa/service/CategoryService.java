package fr.istic.tpjpa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.istic.tpjpa.dao.GenericDao;
import fr.istic.tpjpa.dao.strategy.ExecuteAndReturnObjStrategy;
import fr.istic.tpjpa.dao.strategy.ExecuteStrategy;
import fr.istic.tpjpa.domain.Category;

public class CategoryService {
	private GenericDao gdao = GenericDao.singleton;
	public static final CategoryService singleton = new CategoryService();	
	private CategoryService(){}
	
	public void createCategory(final Category c){
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				gdao.persistObj(c);				
			}
		});
	}
	
	public Category findCategory(final String category){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@Override
			public <T> T execute() {
				
				Map<String, String> params = new HashMap<String, String>();
				params.put("category", category);
				return gdao.findObj("Category.byCategory", params);
			}
		});
	}
	
	public List<Category> listCategory( ){
		final List<Category> categories = new ArrayList<Category>();
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				
				categories.addAll(gdao.listAll(Category.class));
			}
		});
		return categories;
	}
	
	public void deleteCategory(final Category c){
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				gdao.removeObj(Category.class, c.getId());				
			}
		});
	}
	
	public Category updateCategory(final Category c){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@SuppressWarnings("unchecked")
			@Override
			public Category execute() {
				return gdao.mergeObj(c);
			}
		});
	}
	
	public int countCategory(){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@SuppressWarnings("unchecked")
			public Integer execute() {
				return gdao.countObj(Category.class);
			}
		});
	}
	
}
