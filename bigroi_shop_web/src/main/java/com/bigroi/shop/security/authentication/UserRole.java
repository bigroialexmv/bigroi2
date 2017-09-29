package com.bigroi.shop.security.authentication;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {

	private static final long serialVersionUID = -3031313389456567388L;
	
	private String role;
	
	public UserRole(String role) {
		this.role = role;
	}
	
	@Override
	public String getAuthority() {		
		return role;
	}

}
