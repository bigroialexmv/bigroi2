package com.bigroi.shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bigroi.shop.dao.PurchaseOrderDao;
import com.bigroi.shop.model.PurchaseOrder;
import com.bigroi.shop.service.PurchaseOrderService;

public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);
	
	private PurchaseOrderDao pod;
	
	public void setPurchaseOrderDao (PurchaseOrderDao pod) {
		this.pod = pod;
	}
	
	@Override
	public void save(PurchaseOrder po) throws Exception {
		pod.save( po );

	}

	@Override
	public PurchaseOrder findById(Integer id) throws Exception {
		return pod.findById(id);
	}

	@Override
	public List<PurchaseOrder> findOrdersByUserId(Integer userId) throws Exception {
		return pod.findOrdersByUserId(userId);
	}

	@Override
	public List<PurchaseOrder> findByOrderStatus(Integer status) throws Exception {
		return pod.findByOrderStatus(status);
	}

	@Override
	public int countAll() throws Exception {
		return pod.countAll();
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		pod.deleteById(id);
		
	}

	

}
