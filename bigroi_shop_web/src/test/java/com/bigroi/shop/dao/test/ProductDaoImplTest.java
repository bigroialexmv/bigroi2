package com.bigroi.shop.dao.test;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.ProductDao;
import com.bigroi.shop.model.Product;
import com.bigroi.shop.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/dao-config.xml")
public class ProductDaoImplTest {
	
	@Autowired
	private ProductDao productDao;

//	@Test
//	public void testSetNpJdbcTemplate() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testFindById() {
		try {
			Product product = productDao.findById(20);
			assertEquals(43,product.getCode().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountAll() {
		fail("Not yet implemented");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Product product = new Product( 42, "Test sony", 1, "Test description", 1);
				
		try{
			productDao.save(product);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}

}
