package com.bigroi.shop.model;

import java.math.BigDecimal;

public class PurchaseOrderProduct {
	
	private Integer orderId;
	
	private Product product;
	
	private int quantity;
	
	private BigDecimal discount;
	
	private BigDecimal totalPrice;
	
		
    public PurchaseOrderProduct( ) {
		
	}
	
	public PurchaseOrderProduct (Product product, int quantity, BigDecimal discount) {
		this.setProduct(product);
		this.setQuantity(quantity);
		this.setDiscount(discount);
		this.setTotalPrice();
	}
	
	@Override
	public String toString() {
		return String.format("Order product {id=%s, product=%s, quantity=%s, discount=%s, price=%s, total price=%s}",
				orderId, product, quantity, discount, product.getPrice(), getTotalPrice());		
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice() {
		if (discount.compareTo(new BigDecimal(0.00)) != 0) 
			totalPrice = product.getPrice().subtract(discount.multiply(product.getPrice()).multiply(new BigDecimal(quantity)).divide(new BigDecimal(100)));
		else
			totalPrice = product.getPrice();
		
	}
}
