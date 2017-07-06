/**
 * 
 */
package com.bigroi.shop.model;

import java.util.Date;
import java.util.List;

/**
 * @author Alexander Medvedev
 *
 */
public class PurchaseOrder {

    private Integer id;
	
	private User user;
	
	private UserAddress deliveryAddress;
	
	private Date deliveryDate;
	
	private Date deliveryDateFrom;
	
	private Date deliveryDateTo;
	
	private Date created;
	
	private Date updated;
	
	private List<Product> products;
	
	private OrderStatus orderStatus;
	
	private String addInfo;
	
	public PurchaseOrder( ){
		
	}
	
    public PurchaseOrder(User user, UserAddress deliveryAddress, Date deliveryDate, List<Product> products) {
		this.setUser(user);
		this.setDeliveryAddress(deliveryAddress);
		this.setDeliveryDate(deliveryDate);
		this.setProducts(products);
	}
    
    public PurchaseOrder(Integer id, User user, UserAddress deliveryAddress, Date deliveryDate, List<Product> products) {
		this.setId(id);
    	this.setUser(user);
		this.setDeliveryAddress(deliveryAddress);
		this.setDeliveryDate(deliveryDate);
		this.setProducts(products);
	}
    
    public PurchaseOrder(User user, UserAddress deliveryAddress, Date deliveryDate, List<Product> products, String addInfo) {
		this.setUser(user);
		this.setDeliveryAddress(deliveryAddress);
		this.setDeliveryDate(deliveryDate);
		this.setProducts(products);
		this.setAddInfo(addInfo);
	}
    
    public PurchaseOrder(Integer id, User user, UserAddress deliveryAddress, Date deliveryDate, List<Product> products, String addInfo) {
		this.setId(id);
    	this.setUser(user);
		this.setDeliveryAddress(deliveryAddress);
		this.setDeliveryDate(deliveryDate);
		this.setProducts(products);
		this.setAddInfo(addInfo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(UserAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getDeliveryDateFrom() {
		return deliveryDateFrom;
	}

	public void setDeliveryDateFrom(Date deliveryDateFrom) {
		this.deliveryDateFrom = deliveryDateFrom;
	}

	public Date getDeliveryDateTo() {
		return deliveryDateTo;
	}

	public void setDeliveryDateTo(Date deliveryDateTo) {
		this.deliveryDateTo = deliveryDateTo;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	private Date getUpdated() {
		return updated;
	}

	private void setUpdated(Date updated) {
		this.updated = updated;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
    
	@Override
	public String toString() {
		return String.format("{id: %s, User: %s, deliveryAddress: %s, deliveryDate: %s, products: %s, addInfo: %s}", 
				getId(), getUser(), getDeliveryAddress(), getDeliveryDate(), getProducts(), getAddInfo());
	}
	
	
}
