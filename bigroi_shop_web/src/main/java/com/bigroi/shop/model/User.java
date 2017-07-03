package com.bigroi.shop.model;

import java.util.Date;
import java.util.List;

public class User {
	
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String phone;
	
	private Date created;
	
	private Date updated;
	
	private List<UserAddress> adresses;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String email, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;		
	}

	public User(Integer id, String firstName, String lastName, String email, String phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	public List<UserAddress> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<UserAddress> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		return String.format("{userId: %s, firstName: %s, lastName: %s, email: %s, phone: %s}", 
				getId(), getFirstName(), getLastName(), getEmail(), getPhone());
	}

}
