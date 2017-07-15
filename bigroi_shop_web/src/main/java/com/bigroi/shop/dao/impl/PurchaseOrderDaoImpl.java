package com.bigroi.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
	
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}
	
	@Autowired
	@Override
	@Transactional
	public void save(PurchaseOrder po, Product product) throws Exception {
		if ( logger.isDebugEnabled() ) {
			logger.debug("saving purchase order " + po);
		}
		
		String sql = null;
		String anotherSql = null;
		if (po.getId() == null) {			
			sql = "INSERT INTO PURCHASE_ORDER(USER_ID, DLRY_ADDR_ID, DLRY_DATE) "
					+ "VALUES (:USER_ID, :DLRY_ADDR_ID, :DLRY_DATE)";
			anotherSql = "INSERT INTO PURCHASE_ORDER_PRODUCT(ORDER_ID, PRODUCT_CODE, PRODUCT_QUANTITY, PRODUCT_PRICE, DISCOUNT) "
					+ "VALUES (:ORDER_ID, :PRODUCT_CODE, :PRODUCT_QUANTITY, :PRODUCT_PRICE, :DISCOUNT)";
		} else {			
			sql = "UPDATE PURCHASE_ORDER SET USER_ID=:USER_ID, DLRY_ADDR_ID=:DLRY_ADDR_ID, DLRY_DATE=:DLRY_DATE WHERE ORDER_ID=:ORDER_ID";
			anotherSql = "UPDATE PURCHASE_ORDER_PRODUCT SET PRODUCT_CODE=:PRODUCT_CODE, PRODUCT_QUANTITY=:PRODUCT_QUANTITY, PRODUCT_PRICE=;PRODUCT_PRICE, DISCOUNT=:DISCOUNT";
		}		
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("USER_ID", po.getUserId())
			.addValue("DLRY_ADDR_ID", po.getDeliveryAddressId())
			.addValue("DLRY_DATE", po.getDeliveryDate());
		
		npJdbcTemplate.update(sql, params);	
		
		SqlParameterSource anotherParams = new MapSqlParameterSource()
				.addValue("ORDER_ID", po.getId())
				.addValue("PRODUCT_CODE", product.getCode())
				.addValue("PRODUCT_QUANTITY", po.getProductQuantity())
				.addValue("PRODUCT_PRICE", product.getPrice())
				.addValue("DISCOUNT", po.getDiscount());
			
			npJdbcTemplate.update(anotherSql, anotherParams);
		
	}

	

	@Override
	public PurchaseOrder findById(Integer id) throws Exception {
		String sql = "SELECT * FROM PURCHASE_ORDER WHERE ORDER_ID=:ORDER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ORDER_ID", id);
		return npJdbcTemplate.queryForObject(sql, params, PurchaseOrder.class);
	}

	@Override
	public List<PurchaseOrder> findOrdersByUserId(Integer userId) throws Exception {
		
		String sql = "SELECT PO.ORDER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, PO.ADDL_INFO,PO.STATUS_CD FROM PURCHASE_ORDER AS PO WHERE USER_ID=:USER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public List<PurchaseOrder> findByOrderStatus(Integer status) throws Exception {
		String sql = "SELECT PO.ORDER_ID, PO.USER_ID, PO.DLRY_ADDR_ID, PO.CRTD_TMS, PO.DLRY_DATE, PO.ADDL_INFO, FROM PURCHASE_ORDER AS PO WHERE STATUS_CD=:STATUS_CD";
		SqlParameterSource params = new MapSqlParameterSource().addValue("STATUS_CD", status);
		return npJdbcTemplate.query(sql, params, new PurchaseOrderRowMapper());
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM PURCHASE_ORDER AS PO ";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		String sql = "DELETE * FROM PURCHASE_ORDER WHERE ORDER_ID=:ORDER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("ORDER_ID", id);
		npJdbcTemplate.queryForObject(sql, params, PurchaseOrder.class);

	}

}
