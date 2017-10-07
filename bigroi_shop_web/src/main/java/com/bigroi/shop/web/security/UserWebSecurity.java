package com.bigroi.shop.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.bigroi.shop.model.User;

@Component("userWebSecurity")
public class UserWebSecurity {
	
	private Logger logger = LoggerFactory.getLogger(UserWebSecurity.class);
	
	public boolean canEditProfile(Authentication authentication, int userId) {
		Object principal = authentication.getPrincipal();
		if (principal == null || !(principal instanceof User) ) {
			logger.error("Failed to obtain principal");
			return false;
		}
		User user = (User) principal;
		return (user.getId() != null) && (user.getId() == userId); 
	}

}
