package com.bigroi.shop.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private Integer id;
	
	@Size(min=1, max=60)
	@Column(name="FIRST_NAME", columnDefinition="char(60)")
	private String firstName;
	
	@Size(min=1, max=100)
	@Column(name="LAST_NAME", columnDefinition="char(60)")
	private String lastName;
	
	@Size(min=1, max=250)
	@Column(name="EMAIL", columnDefinition="char(256)")	
	private String email;
	
	@NotEmpty
	@Size(min=10, max=40)
	@Column(name="PHONE")
	private String phone;
	
	@Transient
	private Date created;
	
	@Transient
	private Date updated;
	
	@OneToMany
	@JoinColumn(name="USER_ID")
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
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public String toString() {
		return String.format("{userId: %s, firstName: %s, lastName: %s, email: %s, phone: %s}", 
				getId(), getFirstName(), getLastName(), getEmail(), getPhone());
	}

}
