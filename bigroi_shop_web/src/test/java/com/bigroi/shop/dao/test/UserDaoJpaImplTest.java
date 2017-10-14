package com.bigroi.shop.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.model.User;
import com.bigroi.shop.model.UserAddress;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/dao-config.xml", "classpath*:/aop-config.xml"})
public class UserDaoJpaImplTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		User user = new User("Test first name", "Test last name", "test-jpa@email.by", "+375294444444");
		try {
			userDao.save(user);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testFindById() {
		try {
			System.out.println("-- findById");
			User user = userDao.findById(20);
			System.out.println(user);
			for(UserAddress address : user.getAdresses()) {
				System.out.println(address);
			}
			System.out.println("-- end of findById");
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testFindAll() {
		try {
			List<User> users = userDao.findAll();
			for (User user : users)
				System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testFindByEmail() {
		try {
			User user = userDao.findByEmail("risus@malesuadafringilla.co.uk");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testFindByFilter() {
		try {
			System.out.println(" -- Find by filter: ");
			UserFilter filter = new UserFilter(); 
//			UserFilter filter = new UserFilter("G", "l");
			List<User> users = userDao.findByFilter(filter);
			
			for (User user : users)
				System.out.println(user);
			System.out.println(" -- end find by filter");
			System.out.println(" -- Count by filter: ");
			int count = userDao.countByFilter(filter);
			System.out.println("  count=" + count);
			System.out.println(" -- end count by filter");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testFindByFilterWithNulls() {
		try {
			System.out.println(" -- Find by nulls: ");
			List<User> users = userDao.findByFilter(new UserFilter());			
			for (User user : users)
				System.out.println(user);
			System.out.println(" -- end find by nulls");
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	@Ignore
	public void testCountAll() {
		try {
			int count = userDao.countAll();
			System.out.println("Count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
