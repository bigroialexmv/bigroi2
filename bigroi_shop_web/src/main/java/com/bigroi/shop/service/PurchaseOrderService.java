/**
 * 
 */
package com.bigroi.shop.service;

import java.util.List;

import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrder;

/**
 * @author Alexander Medvedev
 *
 */
public interface PurchaseOrderService {
	
	    public void save(PurchaseOrder po) throws Exception;
		
		public PurchaseOrder findById(Integer id) throws Exception;
		
		public List<PurchaseOrder> findOrdersByUserId(Integer userId, PageableFilter filter) throws Exception;
		
		public List<PurchaseOrder> findByOrderStatus(Integer status) throws Exception;
		
		public int countAll() throws Exception;
		
		public void deleteById (Integer id) throws Exception;
		
		public Page<PurchaseOrder> findByFilter(Integer userId, PageableFilter filter) throws Exception;

				
}
