package fr.istic.tpjpa.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({@NamedQuery(name="Category.byCategory", query="select m from Category m where category = :category")})
public class Category implements Serializable{
	private static final long serialVersionUID = 611646801204202332L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="category")
	private Collection<Blog> blogs;
	
	private String category;
	
	public static Category build(){
		return new Category();
	}

	public int getId() {
		return id;
	}

	public Category setId(int id) {
		this.id = id;
		return this;
	}

	public Collection<Blog> getBlogs() {
		return blogs;
	}

	public Category setBlogs(Collection<Blog> blogs) {
		this.blogs = blogs;
		return this;
	}

	public String getCategory() {
		return category;
	}

	public Category setCategory(String category) {
		this.category = category;
		return this;
	}
	
	
}
