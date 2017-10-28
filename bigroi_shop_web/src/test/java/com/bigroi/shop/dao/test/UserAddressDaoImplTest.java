package com.bigroi.shop.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.UserAddressDao;
import com.bigroi.shop.model.UserAddress;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/dao-config.xml")
public class UserAddressDaoImplTest {
	
	@Autowired
	private UserAddressDao userAddressDao;
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByUserId() {
		
		try {
			List<UserAddress> addresses = userAddressDao.findByUserId(20);
			for (UserAddress ua : addresses) {
				System.out.println(ua);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testFindByAddrId() {
		
		try {
			UserAddress userAddress = userAddressDao.findByAddrId(4);
			assertEquals("inconsistent id", 4, userAddress.getAddressId().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		UserAddress userAddress = new  UserAddress(50, 72, "проспект Независимости, 151", "Минск", "Беларусь");
		try {
			userAddressDao.save(userAddress);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Ignore
	@Test
	public void testSaveListOfUserAddress() {
		fail("Not yet implemented");
	}

}
