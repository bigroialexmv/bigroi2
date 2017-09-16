package com.bigroi.shop.service;

import java.util.List;

import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrderProduct;

public interface PurchaseOrderProductService {

	 public List<PurchaseOrderProduct> findPurchaseOrderPoductByOrderId (Integer id, PageableFilter filter) throws Exception;
	 
	 public Page<PurchaseOrderProduct> findByFilter(Integer Id, PageableFilter filter) throws Exception;
}
