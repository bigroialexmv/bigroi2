package com.bigroi.shop.security.authentication;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bigroi.shop.model.User;
import com.bigroi.shop.model.UserCredentials;
import com.bigroi.shop.service.PasswordEncryptionService;
import com.bigroi.shop.service.UserCredentialsService;
import com.bigroi.shop.service.UserService;

public class LocalAuthenticationProvider implements AuthenticationProvider {
	
	private Logger logger = LoggerFactory.getLogger(LocalAuthenticationProvider.class);
	
	@Autowired
	private PasswordEncryptionService passwordEncryptionService;
	
	@Autowired
	private UserCredentialsService userCredentialsService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> target) {
		return UsernamePasswordAuthenticationToken.class.equals(target);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = authentication.getName();
		UserCredentials userCredentials = findUserCredentials(email);
        
        authenticate(authentication, userCredentials);
        
        User user = findUser( userCredentials.getUserId() );
        
        List<GrantedAuthority> authorities = authorizeUser(user);
        
        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authorities);
	}
	
	private UserCredentials findUserCredentials(String email) {
		UserCredentials userCredentials = null;
        try {
        	userCredentials = userCredentialsService.findCredentialsByEmail(email);        	        	
        } catch (Exception e) {
        	logger.error("Failed to obtain user credentials", e);
        	throw new AuthenticationServiceException("Failed to obtain user credentials", e);
        }
        if (userCredentials == null) {
    		throw new UsernameNotFoundException("User not found by email");
    	}
		return userCredentials;
	}
	
	private void authenticate(Authentication authentication, UserCredentials userCredentials) {
		String password = authentication.getCredentials().toString();
        byte[] encryptedPassword = userCredentials.getEncryptedPassword();
        byte[] salt = userCredentials.getSalt();
        try {
			boolean authenticated = passwordEncryptionService.authenticate(password, encryptedPassword, salt);
			if ( !authenticated ) {
				throw new BadCredentialsException("Bad credentials");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new AuthenticationServiceException("Failed to verify user credentials", e);
		}
	}
	
	private User findUser(int userId) {
		User user = null;
        try {        	
			user = userService.findUserById(userId);
		} catch (Exception e) {
			throw new AuthenticationServiceException("Failed to obtain user", e);
		}
		return user;
	}	

	private List<GrantedAuthority> authorizeUser(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new UserRole("USER"));
		return authorities;
	}	

}
