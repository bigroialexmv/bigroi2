package com.bigroi.shop.service;

import java.util.List;

import com.bigroi.shop.model.PurchaseOrderProduct;

public interface PurchaseOrderProductService {

	 public List<PurchaseOrderProduct> findPurchaseOrderPoductByOrderId (Integer id) throws Exception;
}
