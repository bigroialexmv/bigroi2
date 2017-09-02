package com.bigroi.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.bigroi.shop.dao.ProductDao;
import com.bigroi.shop.dao.PurchaseOrderProductDao;
import com.bigroi.shop.model.Product;
import com.bigroi.shop.model.PurchaseOrderProduct;

public class PurchaseOrderProductDaoImpl implements PurchaseOrderProductDao {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderProductDaoImpl.class);
	
	protected final class PurchaseOrderProductRowMapper implements RowMapper<PurchaseOrderProduct> {
		public PurchaseOrderProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
			PurchaseOrderProduct pop = new PurchaseOrderProduct();
			ProductDao productDao = new ProductDaoImpl();
			Product product = new Product();
			try {
				product = productDao.findById(rs.getInt("PRODUCT_CODE"));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pop.setProduct(product);
			pop.setQuantity(rs.getInt("PRODUCT_QUANTITY"));
			pop.setDiscount(rs.getBigDecimal("DISCOUNT"));

			return pop;
		}
	}
	
	
	
	
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	@Override
	public List<PurchaseOrderProduct> findPurchaseOrderPoductByOrderId(Integer id) throws Exception {
		String sql = "SELECT POP.PRODUCT_CODE, POP.PRODUCT_QUANTITY,"
				+ " POP.DISCOUNT FROM PURCHASE_ORDER_PRODUCT"
				+ " AS POP WHERE ORDER_ID=:ORDER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ORDER_ID", id);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderProductRowMapper());
	}

	
}