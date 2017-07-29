/**
 * 
 */
package com.bigroi.shop.dao;

import java.util.List;

import com.bigroi.shop.model.Product;
import com.bigroi.shop.model.PurchaseOrder;


/**
 * @author Alexander Medvedev
 *
 */
public interface PurchaseOrderDao {

    public void save(PurchaseOrder po, List<Product> products) throws Exception;
	
	public PurchaseOrder findById(Integer id) throws Exception;
	
	public List<PurchaseOrder> findOrdersByUserId(Integer userId) throws Exception;
	
	public List<PurchaseOrder> findByOrderStatus(Integer status) throws Exception;
	
	public int countAll() throws Exception;
	
	public List<Product> findPoductsById (Integer Id) throws Exception; 
	
	public void deleteById (Integer id) throws Exception;
}
