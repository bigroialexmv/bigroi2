package com.bigroi.shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.ProductDao;
import com.bigroi.shop.model.Product;
import com.bigroi.shop.service.ProductService;


public class ProductServiceImpl implements ProductService{
	
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private ProductDao productDao;
	
//	@Autowired
//	private ProductService productService;
//	@RequestMapping(path="/product", method = RequestMethod.GET)
//	public String showProduct(ModelMap model) throws Exception{
		public void setProductDao(ProductDao productDao){
			this.productDao = productDao;
	
	

}
	@Override
	@Transactional
	public void save(Product product) throws Exception {
		productDao.save(product);
		
	}
	@Override
	public Product findById(int code) throws Exception {	
		return productDao.findById(code);
	}
	@Override
	public List<Product> findAll() throws Exception {
		logger.debug("withdraw all: " + productDao);
		return productDao.findAll();
	}
	@Override
	public int countAll() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
