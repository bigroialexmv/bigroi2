package com.bigroi.shop.service.impl;

import java.util.List;

import com.bigroi.shop.dao.PurchaseOrderProductDao;
import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrder;
import com.bigroi.shop.model.PurchaseOrderProduct;
import com.bigroi.shop.service.PurchaseOrderProductService;


public class PurchaseOrderProductServiceImpl implements PurchaseOrderProductService {

	PurchaseOrderProductDao popDao;
	
	public void setPurchaseOrderProductDao (PurchaseOrderProductDao popDao) {
		this.popDao = popDao;
	}

	@Override
	public List<PurchaseOrderProduct> findPurchaseOrderPoductByOrderId(Integer id, PageableFilter filter) throws Exception {
		
		return popDao.findPurchaseOrderPoductByOrderId(id, filter);
	}

	@Override
	public Page<PurchaseOrderProduct> findByFilter(Integer id, PageableFilter filter) throws Exception {
		List<PurchaseOrderProduct> purchaseOrderProducts = popDao.findPurchaseOrderPoductByOrderId(id, filter);
		int totalPurchaseOrderProductsCount = popDao.countAll();
		return new Page<PurchaseOrderProduct>(purchaseOrderProducts, totalPurchaseOrderProductsCount, filter);
	}

	
	
} 

