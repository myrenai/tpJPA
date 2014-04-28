#TP GWT + JPA + Maven

### 1. project structure : 

* tpJPA (Parent Project, package type = pom)
* core (Module : JPA part,  package type = jar)
* myBlog (Module GWT part, package type = war)

###2. core Module
* hibernate 4.3.0 Final
* hsqldb 2.2.8
* dto (User+Address(embedded), Category, Blog)
* GenericDao
	* ExecuteAndReturnObjStrategy
    * ExecuteStrategy

code example :
```java
	/**
	 * This method provide a transaction boundary and execute the 
	 * given strategy's method in one transaction context
	 * This method returns a T type object
	 * @param s Strategy callback to execute
	 * @return result obj
	 */
	public <T> T executeAndReturn(ExecuteAndReturnObjStrategy s){
		em = factory.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		
		T t = s.execute();
		
		tx.commit();
		em.close();
		
		return t;
	}
``` 

* UserService, CategorService, BlogService

code example : 
```java
public Blog createBlog(final Blog blog){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@SuppressWarnings("unchecked")
			@Override
			public Blog execute() {
				
				Map<String, String> params = new HashMap<String, String>();
				params.put("userId", blog.getUser().getUserId());
				User user = gdao.findObj("User.byUserId", params);
				
				params = new HashMap<String, String>();
				params.put("category", blog.getCategory().getCategory());
				Category c = gdao.findObj("Category.byCategory", params);
				
				blog.setUser(user).setCategory(c);
				
				return (Blog) gdao.mergeObj(blog);
			}
		});
	}
```

* JUnit test 
code example : 
```java
	@Test
	public void transactionBoundaryTest(){
		
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				
				gdao.persistObj(Utils.makeMockUser("Jiyoung", "Park", "myrenai"));
				
				Map<String, String> params = new HashMap<String, String>();
				params.put("userId", "myrenai");
				User user = gdao.findObj("User.byUserId", params);
				
				assertNotNull(user);
				
				assertEquals(1, gdao.countObj(User.class));
				assertEquals(1, gdao.listAll(User.class).size());
				
				gdao.removeObj(User.class, user.getId());
				
				assertEquals(0, gdao.countObj(User.class));
				
			}
		});
	}
```
        

###3. myBlog Module
 * Maven project
 * gwt(2.6.0) , gwt-maven-plugin(2.6.0)
 * fonctions : 
    * Sign up    
    * Log in
    * Create Category
    * List Category
    * Create Blog
    * List Blog
 * GWT Components : 
 	* Dock Panel
    * Decorator Panel
 * Pages :
 	* SignUpPage
    * LoginPage
    * CategoryListPage
    * BlogFrame
    * BlogListPage
    * BasePage
    * MainView
    

