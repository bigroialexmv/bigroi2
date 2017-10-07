package com.bigroi.shop.filters;

public class UserFilter extends PageableFilter {
	
	private String lastName;
	
	private String email;
	
	public UserFilter() {
		
	}
	
	public UserFilter(String lastName) {
		super();
		this.lastName = lastName;
	}

	public UserFilter(String lastName, String email) {
		super();
		this.lastName = lastName;
		this.email = email;
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
	
	public String getParams() {
		StringBuilder paramsBuilder = new StringBuilder();
		if (lastName != null) {
			paramsBuilder.append("&lastName=").append(lastName);
		}
		if (email != null) {
			paramsBuilder.append("&email=").append(email);
		}
		return paramsBuilder.toString();
	}

}
