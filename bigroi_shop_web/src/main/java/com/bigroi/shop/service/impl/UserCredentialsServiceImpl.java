package com.bigroi.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bigroi.shop.dao.UserCredentialsDao;
import com.bigroi.shop.model.UserCredentials;
import com.bigroi.shop.service.UserCredentialsService;

public class UserCredentialsServiceImpl implements UserCredentialsService {
	
	@Autowired
	private UserCredentialsDao credentialsDao;

	@Override
	public UserCredentials findCredentialsByEmail(String email) throws Exception {		
		return credentialsDao.findByEmail(email);
	}

}
