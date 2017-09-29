package com.bigroi.shop.dao;

import com.bigroi.shop.model.VerificationToken;

public interface VerificationTokenDao {

	public Integer findUserIdByVerificationToken(String verificationToken) throws Exception;
	
	public boolean deleteVerificationToken(String verificationToken) throws Exception;

	public void save(VerificationToken verificationToken);
}
