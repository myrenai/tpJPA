package fr.istic.tpjpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import fr.istic.tpjpa.domain.User;

public class UserDao{
	public static final UserDao singleton = new UserDao();
	private UserDao(){ }
	
	/**
	 * Save user
	 * @param user
	 */
	public void saveUser(EntityManager em, User user){
		em.persist(user);
	}
	
	/**
	 * Find user by userId
	 * @param userId
	 * @return
	 */
	public User findUser(EntityManager em, String userId){
		Query q = em.createQuery("select m from User m where userId = :userId");
		q.setParameter("userId", userId);
		User user = (User) q.getSingleResult();
		return user;
	}
	
	/**
	 * Update user
	 * @param user
	 * @return
	 */
	public User updateUser(EntityManager em, User user){
		return em.merge(user);
	}
	
	/**
	 * Return user count
	 * @return
	 */
	public int countUser(EntityManager em){
		Query q = em.createQuery("select count(m) from User m");
		return ( (Long) q.getSingleResult() ).intValue();
	}
	
	
	public void deleteUser(EntityManager em, User user){
		em.remove(em.find(User.class, user.getId()));
	}
	
	@SuppressWarnings("unchecked")
	public List<User> listUser(EntityManager em){
		Query q = em.createQuery("select m from user m");
		List<User> users = new ArrayList<User>();
		users.addAll(q.getResultList());
		return users;
	}

}
