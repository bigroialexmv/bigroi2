package com.bigroi.shop.dao.test;

import static org.junit.Assert.*;

import java.util.List;

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
			Product product = productDao.findById(41);
			assertEquals(41,product.getCode().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindAll() {
		try {
			List<Product> products = productDao.findAll();
			for(Product product : products) {
				System.out.println(product);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testCountAll() {
		try {
			int count = productDao.countAll();
			System.out.println("Product count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
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
