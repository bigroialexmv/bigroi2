/**
 * 
 */
package com.bigroi.shop.dao;

import java.util.List;

import com.bigroi.shop.model.UserAddress;

/**
 * @author Alexander Medvedev
 *
 */
public interface UserAddressDao {
	
	public List<UserAddress> findByUserId(int userId) throws Exception;
	
	public void save(UserAddress adress) throws Exception;
	
	public Object save(List<UserAddress> adresses) throws Exception;

}
