/**
 * 
 */
package com.bigroi.shop.dao;

import java.util.List;

import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrder;
import com.bigroi.shop.model.UserAddress;


/**
 * @author Alexander Medvedev
 *
 */
public interface PurchaseOrderDao {

    public void save(PurchaseOrder po) throws Exception;
	
	public PurchaseOrder findById(Integer id) throws Exception;
	
	public List<PurchaseOrder> findOrdersByUserId(Integer userId, PageableFilter filter) throws Exception;
	
	public List<PurchaseOrder> findByOrderStatus(Integer statusCode) throws Exception;
	
	public int countAll() throws Exception;
	
	//	public UserAddress findUserAddressByDeliveryAddressId (Integer deliveryAddressId);
	
	public boolean deleteById (Integer id) throws Exception;
}
