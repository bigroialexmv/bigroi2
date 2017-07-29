package com.bigroi.shop.model;

public class ShoppingCartItem {
	
	private Integer productCode;
	
	private Integer quantity;
	
	public ShoppingCartItem(Integer productCode, Integer quantity) {
		super();
		this.productCode = productCode;
		this.quantity = quantity;
	}
	
	public ShoppingCartItem(Integer productCode) {
		this(productCode, 1);		
	}

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
