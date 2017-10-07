package com.bigroi.shop.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.model.User;

public class UserDaoJpaImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(User user) throws Exception {
		entityManager.persist(user);
	}

	@Override
	public User findById(int userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByFilter(UserFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByFilter(UserFilter filter) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
