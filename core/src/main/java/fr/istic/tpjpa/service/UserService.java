package fr.istic.tpjpa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.istic.tpjpa.dao.GenericDao;
import fr.istic.tpjpa.dao.strategy.ExecuteAndReturnObjStrategy;
import fr.istic.tpjpa.dao.strategy.ExecuteStrategy;
import fr.istic.tpjpa.domain.User;

public class UserService {
	
	private GenericDao gdao = GenericDao.singleton;
	public static final UserService singleton = new UserService();	
	private UserService(){}
	
	public void createUser(final User user){
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				
				gdao.persistObj(user);				
			}
		});
	}
	
	public void updateUser(final User user){		
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				
				gdao.mergeObj(user);				
			}
		});
	}
	
	public void deleteUser(final User user){		
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				
				gdao.removeObj(User.class, user.getId());				
			}
		});
	}
	
	public List<User> listAllUsers(){
		final List<User> users = new ArrayList<User>();
		gdao.execute(new ExecuteStrategy(){
			public void execute() {
				
				users.addAll(gdao.listAll(User.class));
			}
		});
		return users;
	}
	
	public User findUser(final String userid){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@Override
			public <T> T execute() {
				
				Map<String, String> params = new HashMap<String, String>();
				params.put("userId", userid);
				return gdao.findObj("User.byUserId", params);
			}
		});
	}
	
	public int countUser(){
		return gdao.executeAndReturn(new ExecuteAndReturnObjStrategy(){
			@SuppressWarnings("unchecked")
			public Integer execute() {
				
				return gdao.countObj(User.class);
			}
		});
	}
}
