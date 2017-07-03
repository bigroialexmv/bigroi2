/**
 * 
 */
package com.bigroi.shop.service;

import com.bigroi.shop.model.PurchaseOrder;

/**
 * @author Alexander Medvedev
 *
 */
public interface PurchaseOrderService {
	
	public void save(PurchaseOrder po) throws Exception;
	
	public PurchaseOrder findById(int poId) throws Exception;

}
