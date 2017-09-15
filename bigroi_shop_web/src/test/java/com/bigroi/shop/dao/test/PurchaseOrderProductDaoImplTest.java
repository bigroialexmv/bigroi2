package com.bigroi.shop.dao.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.PurchaseOrderProductDao;
import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrderProduct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:/dao-config.xml")

public class PurchaseOrderProductDaoImplTest {

	@Autowired
	private PurchaseOrderProductDao popDao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindPurchaseOrderPoductByOrderId() {
		try {
			List<PurchaseOrderProduct> p  = popDao.findPurchaseOrderPoductByOrderId(32, new PageableFilter());
			for(PurchaseOrderProduct product : p) {
				System.out.println(product);
			}
		} catch (Exception e) {			
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
