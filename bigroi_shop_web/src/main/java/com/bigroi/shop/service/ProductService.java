package com.bigroi.shop.service;

import java.util.List;

import com.bigroi.shop.model.Product;


public interface ProductService {
	
	public void save (Product product) throws Exception;
	
	public Product findById (int code) throws Exception;
	
	public List <Product> findAll() throws Exception;
	
	public int countAll() throws Exception;
	
 
}
