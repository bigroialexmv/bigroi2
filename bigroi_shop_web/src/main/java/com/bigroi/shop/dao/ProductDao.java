/**
 * 
 */
package com.bigroi.shop.dao;

import java.util.List;

import com.bigroi.shop.model.Product;

/**
 * @author Alexander Medvedev
 *
 */
public interface ProductDao {

	public Product findById(int productId) throws Exception;
	
	public List<Product> findAll() throws Exception;
	
	public int countAll() throws Exception;
}
