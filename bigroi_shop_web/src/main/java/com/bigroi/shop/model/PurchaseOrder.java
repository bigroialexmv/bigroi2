/**
 * 
 */
package com.bigroi.shop.model;

import java.util.Date;

/**
 * @author Alexander Medvedev
 *
 */
public class PurchaseOrder {

    private Integer id;
	
	private Integer userId;
	
	private Integer deliveryAddressId;
	
	private Date deliveryDate;
	
	private Date deliveryDateFrom;
	
	private Date deliveryDateTo;
	
	private Date created;
	
	private Date updated;
	
	private Product product;
	
	private OrderStatus orderStatus;
	
	private String addInfo;
	
	private int status;
	
	private int productQuantity;
	
	private int discount;
	
	public PurchaseOrder( ){
		
	}
	
    public PurchaseOrder(Integer userId, Integer deliveryAddressId, Date created, Date deliveryDate) {
		this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setCreated(created);
		this.setDeliveryDate(deliveryDate);
		
	}
    
    public PurchaseOrder(Integer id, Integer userId, Integer deliveryAddressId, Date created, Date deliveryDate) {
		this.setId(id);
    	this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setCreated(created);
		this.setDeliveryDate(deliveryDate);
		
	}
    
    public PurchaseOrder(Integer userId, Integer deliveryAddressId, Date created, Date deliveryDate, String addInfo) {
		this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setCreated(created);
		this.setDeliveryDate(deliveryDate);
		
		this.setAddInfo(addInfo);
	}
    
    public PurchaseOrder(Integer id, Integer userId, Integer deliveryAddressId, Date created, Date deliveryDate, String addInfo, int status) {
		this.setId(id);
    	this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setCreated(created);
		this.setDeliveryDate(deliveryDate);
		
		this.setAddInfo(addInfo);
		this.setStatus(status);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(Integer deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int status) {
		orderStatus = OrderStatus.findByStatus(status);
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
    
	@Override
	public String toString() {
		return String.format("{id: %s, userId: %s, deliveryAddress: %s, deliveryDate: %s, product: %s, addInfo: %s}", 
				getId(), getUserId(), getDeliveryAddressId(), getDeliveryDate(), getProduct(), getAddInfo());
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
