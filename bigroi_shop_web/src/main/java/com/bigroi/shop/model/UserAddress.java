/**
 * 
 */
package com.bigroi.shop.model;

/**
 * @author Alexander Medvedev
 *
 */
public class UserAddress {
	
	private Integer userId;
	
	private String streetAddr;
	
	private String city;
	
	private String country;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStreetAddr() {
		return streetAddr;
	}

	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}



}
