package com.bigroi.shop.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.PurchaseOrderDao;
import com.bigroi.shop.model.Product;
import com.bigroi.shop.model.PurchaseOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/dao-config.xml")

public class PurchaseOrderDaoImplTest {

	@Autowired
	private PurchaseOrderDao pod;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Date dateDelivery = new Date();
		List<Product> products = new ArrayList<Product>();
		Product product1 = new Product (56,"MEIZU M3 Note 16GB Gray", new BigDecimal(320) );
		Product product2 = new Product (63,"ZTE Blade A510 Blue", new BigDecimal(199) );
		products.add(product1);
		products.add(product2);
		
		
		PurchaseOrder po = new PurchaseOrder(20, 4, dateDelivery);
		try {
			pod.save(po, products);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	
		
	}

	@Test
	public void testFindById() {
		try {
			PurchaseOrder po = pod.findById(33);
			assertEquals("inconsistent id", 33, po.getId().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindOrdersByUserId() {
		try {
			List<PurchaseOrder> pos = pod.findOrdersByUserId(22);
			for(PurchaseOrder purchaseOrder : pos) {
				System.out.println(purchaseOrder);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindByOrderStatus() {
		try {
			List<PurchaseOrder> pos = pod.findByOrderStatus(1);
			for(PurchaseOrder purchaseOrder : pos) {
				System.out.println(purchaseOrder);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCountAll() {
		try {
			int count = pod.countAll();
			System.out.println("Purchase orders count: " + count);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testFindProductsById() {
		try {
			List<Product> p  = pod.findPoductsById(1);
			for(Product product : p) {
				System.out.println(product);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteById() {
		try {
			pod.deleteById(34);
			System.out.println("Purchase orders number 34 deleted: " + (pod.findById(34) == null));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
