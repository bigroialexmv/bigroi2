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
import com.bigroi.shop.filters.PageableFilter;
import com.bigroi.shop.model.PurchaseOrder;
import com.bigroi.shop.model.PurchaseOrderProduct;
import com.bigroi.shop.model.UserAddress;

public class PurchaseOrderDaoImpl implements PurchaseOrderDao {

	private Logger logger = LoggerFactory.getLogger(PurchaseOrderDaoImpl.class);
	
	protected final class PurchaseOrderRowMapper implements RowMapper<PurchaseOrder> {
		public PurchaseOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
			PurchaseOrder po = new PurchaseOrder();
			UserAddress userAddress = new UserAddress();
			
			po.setId(rs.getInt("ORDER_ID"));
			po.setUserId(rs.getInt("USER_ID"));
			po.setDeliveryAddressId(rs.getInt("DLRY_ADDR_ID"));
			
			userAddress.setUserId(rs.getInt("USER_ID"));
			userAddress.setAddressId(rs.getInt("ADDR_ID"));
			userAddress.setStreetAddr(rs.getString("STREET_ADDR"));
			userAddress.setCity(rs.getString("CITY"));
			userAddress.setCountry(rs.getString("COUNTRY"));
			
			po.setDeliveryAddress(userAddress);
			po.setCreated(rs.getDate("CRTD_TMS"));
			po.setDeliveryDate(rs.getDate("DLRY_DATE"));
			po.setDeliveryTimeFrom(rs.getTime("DLRY_TIME_FROM"));
			po.setDeliveryTimeTo(rs.getTime("DLRY_TIME_TO"));
			po.setAddInfo(rs.getString("ADDL_INFO"));
			
			return po;
		}
	}
	
	
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}	
	
	@Override
	@Transactional
	public void save(PurchaseOrder po) throws Exception {
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
		
		List<PurchaseOrderProduct> products = po.getPurchaseOrderProducts();
		for (PurchaseOrderProduct p : products) {
			    SqlParameterSource productParams = new MapSqlParameterSource()
				.addValue("ORDER_ID", po.getId())
				.addValue("PRODUCT_CODE", p.getProduct().getCode())
				.addValue("PRODUCT_QUANTITY", p.getQuantity())
				.addValue("PRODUCT_PRICE", p.getProduct().getPrice())
				.addValue("DISCOUNT", p.getDiscount());
			
			npJdbcTemplate.update(purchaseOrderProductSql, productParams);
		
	}
	}

	

	@Override
	public PurchaseOrder findById(Integer id) throws Exception {
		String sql = "SELECT PO.ORDER_ID, PO.USER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, " 
				+ "PO.DLRY_TIME_FROM, PO.DLRY_TIME_TO, PO.ADDL_INFO, UA.ADDR_ID, UA.STREET_ADDR, UA.CITY, UA.COUNTRY "
				+ "FROM PURCHASE_ORDER AS PO "
				+ "INNER JOIN  USER_ADDRESS AS UA ON PO.USER_ID = UA.USER_ID "
				+ "WHERE ORDER_ID=:ORDER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ORDER_ID", id);
		return npJdbcTemplate.queryForObject(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public List<PurchaseOrder> findOrdersByUserId(Integer userId, PageableFilter filter) throws Exception {
		
		String sql = "SELECT PO.ORDER_ID, PO.USER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, " 
				+ "PO.DLRY_TIME_FROM, PO.DLRY_TIME_TO, PO.ADDL_INFO, UA.ADDR_ID, UA.STREET_ADDR, UA.CITY, UA.COUNTRY "
				+ "FROM PURCHASE_ORDER AS PO "
				+ "INNER JOIN  USER_ADDRESS AS UA ON PO.USER_ID = UA.USER_ID "
				+ "WHERE PO.USER_ID=:USER_ID"
				+ " LIMIT " + filter.getStart() + ", " + filter.getCount();
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);
		logger.trace(sql);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public List<PurchaseOrder> findByOrderStatus(Integer status) throws Exception {
		String sql = "SELECT PO.ORDER_ID, PO.USER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, " 
				+ "PO.DLRY_TIME_FROM, PO.DLRY_TIME_TO, PO.ADDL_INFO, UA.ADDR_ID, UA.STREET_ADDR, UA.CITY, UA.COUNTRY "
				+ "FROM PURCHASE_ORDER AS PO "
				+ "INNER JOIN  USER_ADDRESS AS UA ON PO.USER_ID = UA.USER_ID "
				+ "WHERE PO.STATUS_CD=:STATUS_CD";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STATUS_CD", status);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM PURCHASE_ORDER AS PO ";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	@Transactional
	public boolean deleteById(Integer id) throws Exception {
		boolean result = false;
		String sqlPO = "DELETE FROM PURCHASE_ORDER WHERE ORDER_ID=:ORDER_ID";
		String sqlPOP = "DELETE FROM PURCHASE_ORDER_PRODUCT WHERE ORDER_ID=:ORDER_ID";
		SqlParameterSource paramsPO = new MapSqlParameterSource().addValue("ORDER_ID", id);
		SqlParameterSource paramsPOP = new MapSqlParameterSource().addValue("ORDER_ID", id);
		npJdbcTemplate.update(sqlPOP, paramsPOP);
		npJdbcTemplate.update(sqlPO, paramsPO);
		result = true;
		return result;
	}
}