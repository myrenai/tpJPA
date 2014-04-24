package fr.istic.tpjpa.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.istic.tpjpa.Utils;
import fr.istic.tpjpa.domain.User;

public class UserTest extends BaseTest{
	@Test
	public void persistUser(){
		
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		User user2 = userDao.findUser(em, user.getUserId());
		
		assertNotNull(user2);
		assertTrue(user.equals(user2));
		assertEquals(1, userDao.countUser(em));
	}
	
	@Test
	public void updateUser(){
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		User user2 = userDao.findUser(em, user.getUserId());
		user2.setUserId("changed!!!");
		user2.setOfficeAddress(Utils.makeMockAddr("office2"));
		userDao.updateUser(em, user2);
		
		User user3 = userDao.findUser(em, "changed!!!");
		assertEquals("office2City",user3.getOfficeAddress().getCity());
		
		assertNotNull(user3);
		assertTrue(user2.equals(user3));
		assertEquals(1, userDao.countUser(em));
	}
	
	@Test
	public void deleteUser(){
		User user = Utils.makeMockUser("Dongyoon", "Kim", "myrenai");
		userDao.saveUser(em, user);
		
		User user2 = userDao.findUser(em, user.getUserId());
		userDao.deleteUser(em, user2);
		
		assertEquals(0, userDao.countUser(em));
	}

	@Test
	public void listeUser(){
		userDao.saveUser(em, Utils.makeMockUser("Dongyoon", "Kim", "dongdong"));
		userDao.saveUser(em, Utils.makeMockUser("Jiyoung", "Park", "myrenai"));
		
		assertEquals(2, userDao.countUser(em));
	}
	
}
