package fr.istic.tpjpa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import fr.istic.sir.tpjpa.dao.GenericDao;
import fr.istic.sir.tpjpa.dao.strategy.ExecuteStrategy;
import fr.istic.sir.tpjpa.domain.User;
import fr.istic.sir.tpjpa.service.UserService;
import fr.istic.tpjpa.Utils;

public class UserServiceTest {
	UserService uservice = UserService.singleton;	
	
	@Test
	public void createUser(){
		uservice.createUser(Utils.makeMockUser("Jiyoung", "Park", "myrenai"));
		assertEquals(1, uservice.countUser());
	}
	
	@Test
	public void updateUser(){
		uservice.createUser(Utils.makeMockUser("Jiyoung", "Park", "myrenai"));
		assertEquals(1, uservice.countUser());
		User user = uservice.findUser("myrenai");
		assertNotNull(user);
		user.setLastName("Kim");
		uservice.updateUser(user);
		User user2 = uservice.findUser("myrenai");
		assertTrue(user.equals(user2));
	}
	
	@Test
	public void deleteUser(){
		uservice.createUser(Utils.makeMockUser("Jiyoung", "Park", "myrenai"));
		assertEquals(1, uservice.countUser());
		User user = uservice.findUser("myrenai");
		assertNotNull(user);
		
		uservice.deleteUser(user);
		assertEquals(0, uservice.countUser());
	}
	
	@Test
	public void listAllUser(){
		uservice.createUser(Utils.makeMockUser("Jiyoung", "Park", "myrenai"));
		assertEquals(1, uservice.countUser());
		User user = uservice.findUser("myrenai");
		assertNotNull(user);
		
		List<User> users = uservice.listAllUsers();
		assertEquals(1, users.size());
	}
	
	@Test
	public void findUser(){
		uservice.createUser(Utils.makeMockUser("Jiyoung", "Park", "myrenai"));
		assertEquals(1, uservice.countUser());
		User user = uservice.findUser("myrenai");
		assertNotNull(user);
	}
	
	@Test
	public void loginUser(){
		uservice.createUser(Utils.makeMockUser("Jiyoung", "Park", "myrenai", "1787"));
		assertEquals(1, uservice.countUser());
		assertTrue(uservice.login("myrenai", "1787"));
	}
	
	private GenericDao gdao = GenericDao.singleton;
	
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
}
