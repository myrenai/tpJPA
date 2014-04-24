package fr.istic.tpjpa.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.istic.tpjpa.dao.strategy.ExecuteAndReturnObjStrategy;
import fr.istic.tpjpa.dao.strategy.ExecuteStrategy;

public class GenericDao{
	
	public static final GenericDao singleton = new GenericDao();
	private GenericDao(){ }
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("tpJPA");	
	private EntityManager em;
	private EntityTransaction tx;
	
	/**
	 * This method provide a transaction boundary and execute the 
	 * given strategy's method in one transaction context
	 * @param s Strategy callback to execute
	 */
	public void execute(ExecuteStrategy s){
		em = factory.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		
		s.execute();
		
		tx.commit();
		em.close();
	}
	
	/**
	 * This method provide a transaction boundary and execute the 
	 * given strategy's method in one transaction context
	 * This method returns a T type object
	 * @param s Strategy callback to execute
	 * @return
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
	
	/**
	 * Merge object
	 * @param obj
	 * @return
	 */
	public <T> T mergeObj(T obj){
		return em.merge(obj);
	}
	
	/*
	 * Persist object
	 */
	public <T> void persistObj(T obj){
		em.persist(obj);
	}
	
	/**
	 * Remove Object
	 * @param obj
	 * @param id
	 */
	public <T> void removeObj(Class<T> obj, int id){
		em.remove(em.find(obj, new Integer(id)));
	}
	
	/**
	 * Return the record count of given table
	 * @param obj
	 * @return
	 */
	public <T> int countObj(Class<T> obj){
		String tableName = obj.getSimpleName();
		Query q = em.createQuery("select count(m) from "+tableName+" m");
		return ((Long) q.getSingleResult()).intValue();
	}
	
	/**
	 * Find obj using namedQuery and params
	 * @param userId
	 * @return
	 */
	public <T> T findObj(String qname, Map<String, String> params){
		Query q = em.createNamedQuery(qname);
		Iterator<String> i = params.keySet().iterator();
		while(i.hasNext()){
			String key = i.next();
			String value = params.get(key);
			q.setParameter(key, value);
		}
		@SuppressWarnings("unchecked")
		T obj =  (T) q.getSingleResult();
		return obj;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> listObj(String qname, Map<String, String> params, Class<T> obj){
		Query q = em.createNamedQuery(qname, obj);
		Iterator<String> i = params.keySet().iterator();
		while(i.hasNext()){
			String key = i.next();
			String value = params.get(key);
			q.setParameter(key, value);
		}
		return q.getResultList();
	}
	
	/**
	 * Return all record of given table
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> listAll(Class<T> obj){
		String tableName = obj.getSimpleName();
		Query q = em.createQuery("select m from "+tableName+" m");
		return q.getResultList();
	}
}
