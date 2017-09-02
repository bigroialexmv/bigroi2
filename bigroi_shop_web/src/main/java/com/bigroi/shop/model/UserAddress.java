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
	
	private Integer addressId;
	
	private String streetAddr;
	
	private String city;
	
	private String country;
	
	public UserAddress() {
		
	}
	
	public UserAddress(Integer userId, Integer addressId, String streetAddr, String city, String country) {
		this.userId = userId;
		this.addressId = addressId;
		this.streetAddr = streetAddr;
		this.city = city;
		this.country = country;
	}

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

	
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	@Override
	public String toString() {
		return String.format("Address: {country=%s, city=%s, street=%s}",
				country, city, streetAddr);		
	}
}
