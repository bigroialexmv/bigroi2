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
import com.bigroi.shop.dao.PurchaseOrderProductDao;
import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.Product;
import com.bigroi.shop.model.PurchaseOrderProduct;

public class PurchaseOrderProductDaoImpl implements PurchaseOrderProductDao {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderProductDaoImpl.class);
	
	protected final class PurchaseOrderProductRowMapper implements RowMapper<PurchaseOrderProduct> {
		public PurchaseOrderProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
			PurchaseOrderProduct pop = new PurchaseOrderProduct();
			Product product = new Product();
			
			product.setCode(rs.getInt("CODE"));
			product.setName(rs.getString("NAME"));
			product.setPrice(rs.getBigDecimal("PRICE"));
			pop.setProduct(product);
			pop.setQuantity(rs.getInt("PRODUCT_QUANTITY"));
			pop.setDiscount(rs.getBigDecimal("DISCOUNT"));
			pop.setTotalPrice();

			return pop;
		}
	}
	
	
	
	
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	@Override
	public List<PurchaseOrderProduct> findPurchaseOrderPoductByOrderId(Integer id, PageableFilter filter) throws Exception {
		String sql = "SELECT P.CODE, P.NAME, P.PRICE, POP.PRODUCT_QUANTITY,"
				+ " POP.DISCOUNT FROM PURCHASE_ORDER_PRODUCT AS POP INNER JOIN PRODUCT AS P ON POP.PRODUCT_CODE = P.CODE"
				+ " WHERE POP.ORDER_ID=:ORDER_ID"
				+ " LIMIT " + filter.getStart() + ", " + filter.getCount();
		SqlParameterSource params = new MapSqlParameterSource().addValue("ORDER_ID", id);
		logger.trace(sql);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderProductRowMapper());
	}
	
	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM PURCHASE_ORDER_PRODUCT";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	
}