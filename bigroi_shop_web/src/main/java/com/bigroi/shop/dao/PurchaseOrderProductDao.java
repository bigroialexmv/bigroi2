package com.bigroi.shop.dao;

import java.util.List;

import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrderProduct;


public interface PurchaseOrderProductDao {
		
	    public List<PurchaseOrderProduct> findPurchaseOrderPoductByOrderId (Integer id, PageableFilter filter) throws Exception;
	    
	   public int countAll() throws Exception;
		
	}


