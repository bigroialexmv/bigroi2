package com.bigroi.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.bigroi.shop.dao.UserAddressDao;
import com.bigroi.shop.model.UserAddress;

public class UserAddressDaoImpl implements UserAddressDao {
	
	
	
	protected final class UserAddressRowMapper implements RowMapper<UserAddress> {
		public UserAddress mapRow(ResultSet  rs3, int rowNum) throws SQLException {
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(rs3.getInt("USER_ID"));
			userAddress.setAddressId(rs3.getInt("ADDR_ID"));
			userAddress.setStreetAddr(rs3.getString("STREET_ADDR"));
			userAddress.setCity(rs3.getString("CITY"));
			userAddress.setCountry(rs3.getString("COUNTRY"));			
			return userAddress;	
		}
	}
			
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	@Override
	public List<UserAddress> findByUserId(int userId) throws Exception {
		String sql = "SELECT  USER_ID, STREET_ADDR, ADDR_ID, CITY, COUNTRY  FROM USER_ADDRESS ";
		return npJdbcTemplate.query(sql, new UserAddressRowMapper());
		
	}

	@Override
	public void save(UserAddress adress) throws Exception {
		String sql = null;
		if (adress.getUserId() == null) {			
			sql = "INSERT INTO USER_ADDRESS(USER_ID, STREET_ADDR, CITY, COUNTRY) VALUES (:USERID, :STREETADDR, :CITY, :COUNTRY)"; 
		} else {			
			sql = "UPDATE USER_ADDRESS SET  STREET_ADDR=:STREET_ADDR, CITY=:CITY, COUNTRY=:COUNTRY WHERE USER_ID=:USER_ID";
		}		
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("USER_ID", adress.getUserId())
			.addValue("ADDR_ID", adress.getAddressId())
			.addValue("STREET_ADDR", adress.getStreetAddr())
			.addValue("CITY", adress.getCity())
			.addValue("COUNTRY", adress.getCountry());	
		npJdbcTemplate.update(sql, params);	
		
	}

	@Override
	public Object save(List<UserAddress> adresses) throws Exception {
			
		String sql = null;
		if (((UserAddress) adresses).getUserId() == null) {			
			sql = "INSERT INTO USER_ADDRESS(USER_ID, STREET_ADDR, CITY, COUNTRY) VALUES (:USER_ID, :STREET_ADDR, :CITY, :COUNTRY)"; 
		} else {			
			sql = "UPDATE USER_ADDRESS SET  STREET_ADDR=:STREET_ADDR, CITY=:CITY, COUNTRY=:COUNTRY WHERE USER_ID=:USER_ID";
		}		
			
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("USER_ID", ((UserAddress) adresses).getUserId())
				.addValue("ADDR_ID", ((UserAddress) adresses).getAddressId())
				.addValue("STREET_ADDR", ((UserAddress) adresses).getStreetAddr())
				.addValue("CITY", ((UserAddress) adresses).getCity())
				.addValue("COUNTRY", ((UserAddress) adresses).getCountry());	
			return npJdbcTemplate.update(sql, params);	
		
	}

	@Override
	public UserAddress findByAddrId(int addrId) throws Exception {
		String sql = "SELECT  USER_ID, ADDR_ID, STREET_ADDR, CITY, COUNTRY  FROM USER_ADDRESS WHERE ADDR_ID =:ADDR_ID";
		SqlParameterSource param = new MapSqlParameterSource().addValue("ADDR_ID", addrId);
		return npJdbcTemplate.queryForObject(sql, param, new UserAddressRowMapper());
	}


}
