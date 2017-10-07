/**
 * 
 */
package com.bigroi.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Alexander Medvedev
 *
 */
@Entity
@Table(name="USER_ADDRESS")
public class UserAddress {
	
	@Column(name="USER_ID")
	private Integer userId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDR_ID")
	private Integer addressId;
	
	@Column(name="STREET_ADDR", columnDefinition="varchar(400)")	
	private String streetAddr;
	
	@Column(columnDefinition="char(255)")
	private String city;
	
	@Column(columnDefinition="text")
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
