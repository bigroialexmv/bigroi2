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
			userAddress.setUserId(rs3.getInt("USERID"));
			userAddress.setStreetAddr(rs3.getString("STREETADDR"));
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
		String sql = "SELECT  USERID, STREETADDR, CITY, COUNTRY,  FROM USERADDRESS ";
		return npJdbcTemplate.query(sql, new UserAddressRowMapper());
		
	}

	@Override
	public void save(UserAddress adress) throws Exception {
		String sql = null;
		if (adress.getUserId() == null) {			
			sql = "INSERT INTO USERADDRESS(USERID, STREETADDR, CITY, COUNTRY) VALUES (:USERID, :STREETADDR, :CITY, :COUNTRY)"; 
		} else {			
			sql = "UPDATE USERADDRESS SET  STREETADDR=:STREETADDR, CITY=:CITY, COUNTRY=:COUNTRY WHERE USERID=:USERID";
		}		
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("USERID", adress.getUserId())
			.addValue("STREETADDR", adress.getStreetAddr())
			.addValue("CITY", adress.getCity())
			.addValue("COUNTRY", adress.getCountry());	
		npJdbcTemplate.update(sql, params);	
		
	}

	@Override
	public Object save(List<UserAddress> adresses) throws Exception {
			
		String sql = null;
		if (((UserAddress) adresses).getUserId() == null) {			
			sql = "INSERT INTO USERADDRESS(USERID, STREETADDR, CITY, COUNTRY) VALUES (:USERID, :STREETADDR, :CITY, :COUNTRY)"; 
		} else {			
			sql = "UPDATE USERADDRESS SET  STREETADDR=:STREETADDR, CITY=:CITY, COUNTRY=:COUNTRY WHERE USERID=:USERID";
		}		
			
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("USERID", ((UserAddress) adresses).getUserId())
				.addValue("STREETADDR", ((UserAddress) adresses).getStreetAddr())
				.addValue("CITY", ((UserAddress) adresses).getCity())
				.addValue("COUNTRY", ((UserAddress) adresses).getCountry());	
			return npJdbcTemplate.update(sql, params);	
		
	}


}
