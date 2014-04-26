package fr.istic.sir.tpjpa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.istic.sir.tpjpa.dao.GenericDao;
import fr.istic.sir.tpjpa.dao.strategy.ExecuteAndReturnObjStrategy;
import fr.istic.sir.tpjpa.dao.strategy.ExecuteStrategy;
import fr.istic.sir.tpjpa.domain.Blog;
import fr.istic.sir.tpjpa.domain.Category;
import fr.istic.sir.tpjpa.domain.User;

public class BlogService {
	private GenericDao gdao = GenericDao.singleton;
	public static final BlogService singleton = new BlogService();	
	private BlogService(){}
	
	public Blog createBlog(final Blog blog){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@SuppressWarnings("unchecked")
			@Override
			public Blog execute() {
				return (Blog) gdao.mergeObj(blog);
			}
		});
	}
	
	public int countBlog(){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@SuppressWarnings("unchecked")
			public Integer execute() {
				return gdao.countObj(Blog.class);
			}
		});
	}
	
	public List<Blog> listBlogsByCategory(final Category c){
		final List<Blog> blogs = new ArrayList<Blog>();
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("category_id", c.getId()+ "");
				blogs.addAll(gdao.listObj("Blog.byCategory", params, Blog.class));
			}
		});
		return blogs;
	}
	
	public List<Blog> listBlogsByUser(final User u){
		final List<Blog> blogs = new ArrayList<Blog>();
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("user_id", u.getId()+ "");
				blogs.addAll(gdao.listObj("Blog.byUser", params, Blog.class));
			}
		});
		return blogs;
	}
	
	public List<Blog> listBlogsByTitle(final String title){
		final List<Blog> blogs = new ArrayList<Blog>();
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("title","%" + title + "%");
				blogs.addAll(gdao.listObj("Blog.byTitle", params, Blog.class));
			}
		});
		return blogs;
	}
	
	public void deleteBlog(final Blog blog){		
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				gdao.removeObj(Blog.class, blog.getId());
			}
		});
	}
	
	
}
