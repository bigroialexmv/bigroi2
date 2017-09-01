package com.bigroi.shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.bigroi.shop.dao.UserAddressDao;
import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.filters.Page;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.model.User;
import com.bigroi.shop.service.UserService;

public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDao userDao;
	
	private UserAddressDao userAddressDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserAddressDao(UserAddressDao userAddressDao) {
		this.userAddressDao = userAddressDao;
	}

	@Override
	public User findUserById(int userId) throws Exception {
		return userDao.findById(userId);
	}

	@Override
	@Transactional
	public void save(User user) throws Exception {
		userDao.save( user );
//		userAddressDao.save( user.getAdresses() );		
	}

	@Override
	public int countAll() throws Exception {
		return userDao.countAll();
	}

	@Override
	public List<User> findAll() throws Exception {
		logger.debug("find all: " + userDao);
		return userDao.findAll();
	}

	@Override
	public Page<UserFilter, User> findUsersPageByFilter(UserFilter filter) throws Exception {
		List<User> users = userDao.findUsersByFilter(filter);
		int allUsersCount = userDao.countAll();		
		Page<UserFilter, User> page = new Page<UserFilter, User>(filter, users, allUsersCount);
		return page;
	}

}
