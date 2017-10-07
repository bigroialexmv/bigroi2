package com.bigroi.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.model.User;

@Repository
public class UserDaoJpaImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(User user) throws Exception {
		entityManager.persist(user);		
	}

	@Override
	public User findById(int userId) throws Exception {
		return entityManager.find(User.class, new Integer(userId));
	}

	@Override
	public List<User> findAll() throws Exception {
		List<User> users = entityManager.createQuery(
	            "SELECT u FROM User u", User.class).getResultList();		
		return users;
	}

	@Override
	public List<User> findByFilter(UserFilter filter) {
		CriteriaQuery<?> cq = buildCriteriaByFilter(filter);
		Query query = entityManager.createQuery(cq);
		query.setFirstResult( filter.getStart() ); 
		query.setMaxResults(filter.getCount() );
		List<User> users = query.getResultList();
		return users;
	}
	
	@Override
	public int countByFilter(UserFilter filter) throws Exception {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); 
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<User> user = cq.from(User.class);
		cq.select(cb.count(user));
		addPredicatesByFilter(filter, cb, cq, user);
		long count = entityManager.createQuery(cq).getSingleResult();
		return (int) count;
	}

	protected CriteriaQuery<?> buildCriteriaByFilter(UserFilter filter) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder(); 
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		return addPredicatesByFilter(filter, cb, cq, cq.from(User.class));
	}

	protected CriteriaQuery<?> addPredicatesByFilter(UserFilter filter, CriteriaBuilder cb,
			CriteriaQuery<?> cq, Root<User> user) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if ( !StringUtils.isEmpty(filter.getLastName()) ) {
			predicates.add( cb.like(user.<String>get("lastName"), filter.getLastName()+"%" ));
		}
		if ( !StringUtils.isEmpty(filter.getEmail()) ) {
			predicates.add( cb.like(user.<String>get("email"), filter.getEmail()+"%" ) );			
		}
		cq.where( predicates.toArray(new Predicate[0]) );
		return cq;
	}

	@Override
	public int countAll() throws Exception {
		Query query = entityManager.createQuery("SELECT count(*) FROM User");
        long count = (long) query.getSingleResult();
        return (int) count;
	}	

	@Override
	public User findByEmail(String email) {
		User user = entityManager.createQuery(
			"SELECT u FROM User u WHERE u.email=:email", User.class)
			.setParameter("email", email).getSingleResult();		
		return user;
	}

}
