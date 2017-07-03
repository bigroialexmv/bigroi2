/**
 * 
 */
package com.bigroi.shop.dao;

import java.util.List;

import com.bigroi.shop.model.User;

/**
 * @author Alexander Medvedev
 *
 */
public interface UserDao {
	
	public void save(User user) throws Exception;
	
	public User findById(int userId) throws Exception;
	
	public List<User> findAll() throws Exception;
	
	public int countAll() throws Exception;

}
