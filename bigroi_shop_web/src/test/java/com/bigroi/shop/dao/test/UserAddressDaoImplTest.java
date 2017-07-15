package com.bigroi.shop.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/dao-config.xml")
public class UserAddressDaoImplTest {
	
	@Autowired
	private UserDao userAddressDao;
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByUserId() {
		
		try {
			User user = userAddressDao.findById(2);
			assertEquals("inconsistent id", 2, user.getId().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	public void testSaveUserAddress() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveListOfUserAddress() {
		fail("Not yet implemented");
	}

}
