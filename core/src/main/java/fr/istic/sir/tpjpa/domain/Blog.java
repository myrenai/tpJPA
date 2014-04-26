package fr.istic.sir.tpjpa.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
			@NamedQuery(name="Blog.byCategory", query="select m from Blog m where category_id =:category_id"), 
			@NamedQuery(name="Blog.byUser", query="select m from Blog m where user_id =:user_id"),
			@NamedQuery(name="Blog.byTitle", query="select m from Blog m where title like :title")
		})
public class Blog implements Serializable{

	private static final long serialVersionUID = -6620126806104258473L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String title;
	@Lob
	private String content;
	
	@ManyToOne
	private User user;
	
	/**
	 * When a blog is saved, the related category is also saved
	 */
	@ManyToOne
	private Category category;
	
	
	public static Blog build(){
		return new Blog();
	}

	public int getId() {
		return id;
	}

	public Blog setId(int id) {
		this.id = id;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Blog setContent(String content) {
		this.content = content;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Blog setUser(User user) {
		this.user = user;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public Blog setCategory(Category category) {
		this.category = category;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Blog setTitle(String title) {
		this.title = title;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blog other = (Blog) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

}
