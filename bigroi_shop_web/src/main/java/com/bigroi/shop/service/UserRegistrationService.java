package com.bigroi.shop.service;

import com.bigroi.shop.model.UserRegistrationData;

public interface UserRegistrationService {

	void register(UserRegistrationData registration) throws Exception;
	
	void confirm(String verificationToken) throws Exception;

}