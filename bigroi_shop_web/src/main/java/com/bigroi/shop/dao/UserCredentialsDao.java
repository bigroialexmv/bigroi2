package com.bigroi.shop.dao;

import com.bigroi.shop.model.UserCredentials;

public interface UserCredentialsDao {
	
	public UserCredentials findByEmail(String email) throws Exception;
	
	public void save(UserCredentials userCredentials) throws Exception;

}
