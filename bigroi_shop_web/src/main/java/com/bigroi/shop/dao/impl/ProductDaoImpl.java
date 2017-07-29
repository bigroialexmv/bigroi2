package com.bigroi.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;


import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.bigroi.shop.dao.ProductDao;
import com.bigroi.shop.model.Product;

public class ProductDaoImpl implements ProductDao {

	
	protected final class ProductRowMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet  rs2, int rowNum) throws SQLException {
			Product product = new Product();
			product.setCode(rs2.getInt("CODE"));
			product.setName(rs2.getString("NAME"));
			product.setPrice(rs2.getBigDecimal("PRICE"));
			product.setDescription(rs2.getString("DESCRIPTION"));			
			product.setQuantity(rs2.getInt("QUANTITY"));
			return product;		
		}
	}
	
	//@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	public void setNpJdbcTemplate (NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}
	@Override
	public Product findById(int code) throws Exception {
		String sql = "SELECT CODE, NAME, PRICE, DESCRIPTION, QUANTITY FROM PRODUCT  WHERE CODE=:CODE";
		SqlParameterSource params = new MapSqlParameterSource().addValue("CODE",code);
		return npJdbcTemplate.queryForObject(sql, params, new ProductRowMapper());
	}

	@Override
	public List<Product> findAll() throws Exception {
		String sql = "SELECT  CODE, NAME, PRICE, DESCRIPTION, QUANTITY FROM PRODUCT ";
		return npJdbcTemplate.query(sql, new ProductRowMapper());
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT  COUNT(*) FROM PRODUCT ";
		return npJdbcTemplate.queryForObject(sql, new  MapSqlParameterSource(), Integer.class);
	}
	@Override
	public void save(Product product) throws Exception {
		String sql = null;
		if (product.getCode()== null){
			sql = "INSERT INTO PRODUCT(CODE, NAME, PRICE, DESCRIPTION, QUANTITY) VALUE (:CODE, :NAME, :PRICE, :DESCRIPTION, :QUANTITY)";
		}else{
			sql ="UPDATE PRODUCT SET  NAME=:NAME, PRICE=:PRICE, DESCRIPTION=:DESCRIPTION, QUANTITY=:QUANTITY WHERE CODE=:CODE" ;
		}
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("CODE", product.getCode())
				.addValue("NAME", product.getName())
				.addValue("PRICE", product.getPrice())
				.addValue("DESCRIPTION", product.getDescription())
				.addValue("QUANTITY", product.getQuantity());
		npJdbcTemplate.update(sql, params);
		}

		
		
	}
	
		
	

