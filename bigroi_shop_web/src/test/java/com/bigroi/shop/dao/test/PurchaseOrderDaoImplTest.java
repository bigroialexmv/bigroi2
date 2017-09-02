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
import com.bigroi.shop.model.PurchaseOrderProduct;

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
		List<PurchaseOrderProduct> products = new ArrayList<PurchaseOrderProduct>();
		Product product1 = new Product (56,"MEIZU M3 Note 16GB Gray", new BigDecimal(320) );
		Product product2 = new Product (63,"ZTE Blade A510 Blue", new BigDecimal(199) );
		PurchaseOrderProduct order1 = new PurchaseOrderProduct(product1, 2, new BigDecimal(2));
		PurchaseOrderProduct order2 = new PurchaseOrderProduct(product2, 1, new BigDecimal (1));
		products.add(order1);
		products.add(order2);
		
		
		PurchaseOrder po = new PurchaseOrder(20, 1, dateDelivery, products);
		try {
			pod.save(po);			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	
		
	}

	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindById() {
		try {
			pod.findById(31);
//			assertEquals("inconsistent id", 31, po.getId().intValue());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	@Rollback(true)
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
	@Transactional
	@Rollback(true)
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
	@Transactional
	@Rollback(true)
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
	@Transactional
	@Rollback(true)
	public void testDeleteById() {
		try {
//			pod.deleteById(32);
			System.out.println("Purchase orders number 32 is deleted: " + pod.findById(32));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
