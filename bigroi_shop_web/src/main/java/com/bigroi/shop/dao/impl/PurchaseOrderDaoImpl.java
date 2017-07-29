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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.PurchaseOrderDao;
import com.bigroi.shop.model.Product;
import com.bigroi.shop.model.PurchaseOrder;

public class PurchaseOrderDaoImpl implements PurchaseOrderDao {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderDaoImpl.class);
	
	protected final class PurchaseOrderRowMapper implements RowMapper<PurchaseOrder> {
		public PurchaseOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
			PurchaseOrder po = new PurchaseOrder();
			po.setId(rs.getInt("ORDER_ID"));
			po.setUserId(rs.getInt("USER_ID"));
//			po.setOrderStatus(rs.getInt("STATUS_CD"));
			po.setDeliveryAddressId(rs.getInt("DLRY_ADDR_ID"));
			po.setCreated(rs.getTimestamp("CRTD_TMS"));
			po.setDeliveryDate(rs.getDate("DLRY_DATE"));
			po.setAddInfo(rs.getString("ADDL_INFO"));
			po.setStatus(rs.getInt("STATUS_CD"));
			return po;
		}
	}
	
	protected final class ProductRowMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product p = new Product();
			p.setCode(rs.getInt("CODE"));
			p.setName(rs.getString("NAME"));
            p.setPrice(rs.getBigDecimal("PRICE"));
			p.setDescription(rs.getString("DESCRIPTION"));
			p.setQuantity(rs.getInt("QUANTITY"));
			
			return p;
		}
	}
	
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}	
	
	@Override
	@Transactional
	public void save(PurchaseOrder po, List<Product> products) throws Exception {
		if ( logger.isDebugEnabled() ) {
			logger.debug("saving purchase order " + po);
		}
		
		String purchaseOrdersql = null;
		String purchaseOrderProductSql = null;
		if (po.getId() == null) {			
			purchaseOrdersql = "INSERT INTO PURCHASE_ORDER(USER_ID, DLRY_ADDR_ID, DLRY_DATE) "
					+ "VALUES (:USER_ID, :DLRY_ADDR_ID, :DLRY_DATE)";
			purchaseOrderProductSql = "INSERT INTO PURCHASE_ORDER_PRODUCT(ORDER_ID, PRODUCT_CODE, PRODUCT_QUANTITY, PRODUCT_PRICE, DISCOUNT) "
					+ "VALUES (:ORDER_ID, :PRODUCT_CODE, :PRODUCT_QUANTITY, :PRODUCT_PRICE, :DISCOUNT)";
		} else {			
			purchaseOrdersql = "UPDATE PURCHASE_ORDER SET USER_ID=:USER_ID, DLRY_ADDR_ID=:DLRY_ADDR_ID, DLRY_DATE=:DLRY_DATE WHERE ORDER_ID=:ORDER_ID";
			purchaseOrderProductSql = "UPDATE PURCHASE_ORDER_PRODUCT SET PRODUCT_CODE=:PRODUCT_CODE, PRODUCT_QUANTITY=:PRODUCT_QUANTITY, PRODUCT_PRICE=;PRODUCT_PRICE, DISCOUNT=:DISCOUNT";
		}		
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("USER_ID", po.getUserId())
			.addValue("DLRY_ADDR_ID", po.getDeliveryAddressId())
			.addValue("DLRY_DATE", po.getDeliveryDate());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		npJdbcTemplate.update(purchaseOrdersql, params, keyHolder);
		Number orderId = keyHolder.getKey();		
		po.setId(orderId.intValue());
		
		for (Product p : products) {
			    SqlParameterSource productParams = new MapSqlParameterSource()
				.addValue("ORDER_ID", po.getId())
				.addValue("PRODUCT_CODE", p.getCode())
				.addValue("PRODUCT_QUANTITY", po.getProductQuantity())
				.addValue("PRODUCT_PRICE", p.getPrice())
				.addValue("DISCOUNT", po.getDiscount());
			
			npJdbcTemplate.update(purchaseOrderProductSql, productParams);
		
	}
	}

	

	@Override
	public PurchaseOrder findById(Integer id) throws Exception {
		String sql = "SELECT PO.USER_ID, PO.ORDER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, PO.ADDL_INFO,PO.STATUS_CD FROM PURCHASE_ORDER AS PO WHERE ORDER_ID=:ORDER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ORDER_ID", id);
		return npJdbcTemplate.queryForObject(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public List<PurchaseOrder> findOrdersByUserId(Integer userId) throws Exception {
		
		String sql = "SELECT PO.USER_ID, PO.ORDER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, PO.ADDL_INFO,PO.STATUS_CD FROM PURCHASE_ORDER AS PO WHERE USER_ID=:USER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public List<PurchaseOrder> findByOrderStatus(Integer status) throws Exception {
		String sql = "SELECT PO.ORDER_ID, PO.STATUS_CD, PO.USER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, PO.ADDL_INFO FROM PURCHASE_ORDER AS PO WHERE PO.STATUS_CD=:STATUS_CD";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STATUS_CD", status);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM PURCHASE_ORDER AS PO ";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public List<Product> findPoductsById (Integer id) throws Exception{
		String sqlProduct = "SELECT POP.PRODUCT_CODE, POP.PRODUCT_PRICE, POP.PRODUCT_QUANTITY FROM PURCHASE_ORDER_PRODUCT AS POP JOIN P.NAME, P.DESCRIPTION FROM PRODUCT AS P ON PRODUCT_CODE = CODE WHERE ORDER_ID =: ORDER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ORDER_ID", id);
		return npJdbcTemplate.query(sqlProduct, params, new ProductRowMapper());
	} 
	
	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		String sqlPO = "DELETE FROM PURCHASE_ORDER WHERE ORDER_ID=:ORDER_ID";
		String sqlPOP = "DELETE FROM PURCHASE_ORDER_PRODUCT WHERE ORDER_ID=:ORDER_ID";
		SqlParameterSource paramsPO = new MapSqlParameterSource().addValue("ORDER_ID", id);
		SqlParameterSource paramsPOP = new MapSqlParameterSource().addValue(sqlPOP, id);
		npJdbcTemplate.update(sqlPOP, paramsPO);
		npJdbcTemplate.update(sqlPO, paramsPOP);

	}

}
