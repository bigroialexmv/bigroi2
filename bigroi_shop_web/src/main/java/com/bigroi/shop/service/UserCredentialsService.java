package com.bigroi.shop.service;

import com.bigroi.shop.model.UserCredentials;

public interface UserCredentialsService {
	
	public UserCredentials findCredentialsByEmail(String email) throws Exception;

}
