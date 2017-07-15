package com.bigroi.shop.model;

import java.math.BigDecimal;

public class Product {
	
	private Integer code;
	
	private String name;
	
	private BigDecimal price;
	
	private String description;
	
	private Integer quantity;

	/**
	 * Creates new product
	 * @param code product code
	 * @param name product name
	 * @param price product price
	 */
	public Product(Integer code, String name, BigDecimal price) {
		super();
		if ( code == null ) {
			throw new IllegalArgumentException("code must not be null");
		}
		if ( name == null ) {
			throw new IllegalArgumentException("name must not be null");
		}
		this.code = code;
		this.name = name;
		this.price = price;
		
	}
	
	public Product(Integer code, String name, double price, String description,Integer quantity) {	
		this(code, name, new BigDecimal(price) );
		this.description = description;
		this.quantity = quantity;
	}



	public Product() {
		
	}



	public void setCode(Integer code) {
		this.code = code;
	}
	
	//public void setCode(Integer code) {
		//this.code = code.toString();
	

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	
	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		String s = String.format("Product {name=%s, code=%s, price=%s}", name, code, price);
		return s;
	}

	public void setName( String name) {
		this.name=name;
		
	}

	public void setPrice(BigDecimal price) {
		this.price=price;
		
	}

	public void setDescription(String description) {
		this.description=description;
		
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
