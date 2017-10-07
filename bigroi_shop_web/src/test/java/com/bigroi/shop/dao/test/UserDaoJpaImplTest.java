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
public class UserDaoJpaImplTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
//	@Rollback(true)
	public void testSave() {
		User user = new User("Test first name", "Test last name", "test@email.by", "+375294444444");
		try {
			userDao.save(user);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
