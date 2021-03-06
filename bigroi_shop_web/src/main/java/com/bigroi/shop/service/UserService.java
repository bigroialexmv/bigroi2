/**
 * 
 */
package com.bigroi.shop.service;

import java.util.List;

import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.model.User;

/**
 * @author Alexander Medvedev
 *
 */
public interface UserService {
	
	public User findUserById(int userId) throws Exception;
	
	public User findUserByEmail(String email) throws Exception;
	
	public void save(User user) throws Exception;
	
	public int countAll() throws Exception;
	
	public List<User> findAll() throws Exception;
	
	public Page<User> findUserPageByFilter(UserFilter filter) throws Exception;
}
