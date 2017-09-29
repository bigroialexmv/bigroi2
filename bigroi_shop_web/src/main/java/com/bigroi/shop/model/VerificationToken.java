package com.bigroi.shop.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class VerificationToken {
	
	private static final int EXPIRATION = 60 * 24;
	 
	private Integer userId;
	
    private String token;
   
    private Date expiryTime;
    
    public VerificationToken(Integer userId) {
    	this.userId = userId;
    	calculateExpiryTime(EXPIRATION);
    }
    
    private Date calculateExpiryTime(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
    
    public Integer getUserId() {
		return userId;
	}

	public String getToken() {
		return token;
	}

	public Date getExpiryTime() {
		return expiryTime;
	}
    
    
}
