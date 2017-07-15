package com.bigroi.shop.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bigroi.shop.dao.UserAddressDao;
import com.bigroi.shop.model.UserAddress;

public class UserAddressDaoImpl implements UserAddressDao {
	
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	@Override
	public List<UserAddress> findByUserId(int userId) throws Exception {
		
		return null;
	}

	@Override
	public void save(UserAddress adress) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(List<UserAddress> adresses) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
