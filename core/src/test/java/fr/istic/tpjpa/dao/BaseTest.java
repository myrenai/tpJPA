package fr.istic.tpjpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import fr.istic.sir.tpjpa.dao.BlogDao;
import fr.istic.sir.tpjpa.dao.CategoryDao;
import fr.istic.sir.tpjpa.dao.GenericDao;
import fr.istic.sir.tpjpa.dao.UserDao;

public class BaseTest {
	
	protected EntityManagerFactory factory = Persistence.createEntityManagerFactory("tpJPA");
	protected EntityManager em;
	protected EntityTransaction tx;
	protected BlogDao blogDao;
	protected UserDao userDao;
	protected CategoryDao categoryDao;
	protected GenericDao gdao;
	
	@Before
	public void setup(){
		blogDao = BlogDao.singleton;
		userDao = UserDao.singleton;
		categoryDao = CategoryDao.singleton;
		gdao  = GenericDao.singleton;
		em = factory.createEntityManager();
		
		tx = em.getTransaction();
		System.out.println("em created!!!");
		tx.begin();
		System.out.println("start tx!!!");
	}
	
	@After
	public void after(){
		tx.commit();
		System.out.println("commit tx!!!");
		em.close();
		System.out.println("em closed!!!");
	}
}
