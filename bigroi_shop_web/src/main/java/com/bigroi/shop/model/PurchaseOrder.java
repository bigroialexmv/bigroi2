/**
 * 
 */
package com.bigroi.shop.model;


import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * @author Alexander Medvedev
 *
 */
public class PurchaseOrder {

    private Integer id;
	
	private Integer userId;
	
	private Integer deliveryAddressId;
	
	private UserAddress deliveryAddress;
	
	private Date deliveryDate;
	
	private Time deliveryTimeFrom;
	
	private Time deliveryTimeTo;
	
	private List<PurchaseOrderProduct> products;
	
	private String addInfo;
	
	private int statusCode;
	
	private Date created;
	
	
	public PurchaseOrder( ){
		
	}
	
    public PurchaseOrder(Integer userId, Integer deliveryAddressId, Date deliveryDate, 
    		List<PurchaseOrderProduct> products) {
		this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setDeliveryDate(deliveryDate);
		this.setPurchaseOrderProducts(products);
				
	}
    
    public PurchaseOrder(Integer userId, Integer deliveryAddressId, Date deliveryDate, 
    		List<PurchaseOrderProduct> products, String addInfo) {
		this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setDeliveryDate(deliveryDate);
		this.setPurchaseOrderProducts(products);
		this.setAddInfo(addInfo);
		
	}
    
    public PurchaseOrder(Integer userId, Integer deliveryAddressId, Date deliveryDate, 
    		List<PurchaseOrderProduct> products, Time deliveryTimeFrom,
    		Time deliveryTimeTo) {
		this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setDeliveryDate(deliveryDate);
		this.setPurchaseOrderProducts(products);
		this.setDeliveryTimeFrom(deliveryTimeFrom);
		this.setDeliveryTimeTo(deliveryTimeTo);
		
	}
    
    public PurchaseOrder(Integer id, Integer userId, Integer deliveryAddressId, UserAddress deliveryAddress, Date created,
    		Date deliveryDate, List<PurchaseOrderProduct> products, Time deliveryTimeFrom, 
    		Time deliveryTimeTo, String addInfo) {
		this.setId(id);
    	this.setUserId(userId);
		this.setDeliveryAddressId(deliveryAddressId);
		this.setDeliveryAddress(deliveryAddress);
		this.setCreated(created);
		this.setDeliveryDate(deliveryDate);
		this.setPurchaseOrderProducts(products);
		this.setDeliveryTimeFrom(deliveryTimeFrom);
		this.setDeliveryTimeTo(deliveryTimeTo);
		this.setAddInfo(addInfo);
		
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

	public Time getDeliveryTimeFrom() {
		return deliveryTimeFrom;
	}

	public void setDeliveryTimeFrom(Time deliveryTimeFrom) {
		this.deliveryTimeFrom = deliveryTimeFrom;
	}

	public Time getDeliveryTimeTo() {
		return deliveryTimeTo;
	}

	public void setDeliveryTimeTo(Time deliveryTimeTo) {
		this.deliveryTimeTo = deliveryTimeTo;
	}

	
	public List<PurchaseOrderProduct> getPurchaseOrderProducts() {
		return products;
	}

	public void setPurchaseOrderProducts(List<PurchaseOrderProduct> products) {
		this.products = products;
	}

    public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
    
	@Override
	public String toString() {
		return String.format("{id: %s, userId: %s, deliveryAddress: %s, deliveryDate: %s, products: %s,"
				+ " addInfo: %s, }", 
				getId(), getUserId(), getDeliveryAddressId(), getDeliveryDate(), getPurchaseOrderProducts(), getAddInfo());
	}

	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public UserAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(UserAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	
}
