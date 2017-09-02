package com.bigroi.shop.service.impl;

import java.util.List;

import com.bigroi.shop.dao.PurchaseOrderProductDao;
import com.bigroi.shop.model.PurchaseOrderProduct;
import com.bigroi.shop.service.PurchaseOrderProductService;


public class PurchaseOrderProductServiceImpl implements PurchaseOrderProductService {

	PurchaseOrderProductDao popDao;
	
	public void setPurchaseOrderProductDao (PurchaseOrderProductDao popDao) {
		this.popDao = popDao;
	}

	public List<PurchaseOrderProduct> findPurchaseOrderPoductByOrderId(Integer id) throws Exception {
		
		return popDao.findPurchaseOrderPoductByOrderId(id);
	}
} 

