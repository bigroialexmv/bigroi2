/**
 * 
 */
package com.bigroi.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.filters.UserFilter;
import com.bigroi.shop.model.User;

/**
 * @author Alexander Medvedev
 *
 */
public class UserDaoImpl implements UserDao {
	
	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	protected final class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("USER_ID"));
			user.setFirstName(rs.getString("FIRST_NAME"));
			user.setLastName(rs.getString("LAST_NAME"));
			user.setEmail(rs.getString("EMAIL"));
			user.setPhone(rs.getString("PHONE"));
			user.setCreated(rs.getTimestamp("CRTD_TMS"));
			user.setUpdated(rs.getTimestamp("UPDT_TMS"));
			return user;
		}
	}

	// @Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;

	public void setNpJdbcTemplate(NamedParameterJdbcTemplate npJdbcTemplate) {
		this.npJdbcTemplate = npJdbcTemplate;
	}

	@Override
	public void save(User user) throws Exception {
		if ( logger.isDebugEnabled() ) {
			logger.debug("saving user " + user);
		}
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("FIRST_NAME", user.getFirstName())
				.addValue("LAST_NAME", user.getLastName())
				.addValue("EMAIL", user.getEmail())
				.addValue("PHONE", user.getPhone())
				.addValue("USER_ID", user.getId());
		
		String sql = null;
		
		if (user.getId() == null) {			
			sql = "INSERT INTO USER(FIRST_NAME, LAST_NAME, EMAIL, PHONE) VALUES (:FIRST_NAME, :LAST_NAME, :EMAIL, :PHONE)";
			KeyHolder genKeyHolder = new GeneratedKeyHolder(); 
			npJdbcTemplate.update(sql, params, genKeyHolder);
			Number key = genKeyHolder.getKey();
			user.setId( key.intValue() );
		} else {			
			sql = "UPDATE USER SET FIRST_NAME=:FIRST_NAME, LAST_NAME=:LAST_NAME, EMAIL=:EMAIL, PHONE=:PHONE WHERE USER_ID=:USER_ID";
			npJdbcTemplate.update(sql, params);
		}
		
	}

	@Override
	public User findById(int userId) throws Exception {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.CRTD_TMS, U.UPDT_TMS, U.PHONE FROM USER AS U WHERE USER_ID=:USER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);
		try {
			return npJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}	

	@Override
	public User findByEmail(String email) {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.CRTD_TMS, U.UPDT_TMS, U.PHONE FROM USER AS U WHERE EMAIL=:EMAIL";
		SqlParameterSource params = new MapSqlParameterSource().addValue("EMAIL", email);
		try {
			return npJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<User> findAll() throws Exception {		
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.CRTD_TMS, U.UPDT_TMS, U.PHONE FROM USER AS U ";
		return npJdbcTemplate.query(sql, new UserRowMapper());		
	}

	@Override
	public int countAll() throws Exception {
		String sql = "SELECT COUNT(*) FROM USER AS U ";
		return npJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
	}

	@Override
	public List<User> findByFilter(UserFilter filter) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder sqlb = new StringBuilder( "SELECT "
				+ "U.USER_ID, "
				+ "U.FIRST_NAME, "
				+ "U.LAST_NAME, "
				+ "U.EMAIL, "
				+ "U.CRTD_TMS, "
				+ "U.UPDT_TMS, "
				+ "U.PHONE "
				+ "FROM USER AS U ");
		
		StringBuilder criteriaBuilder = new StringBuilder();
		
		if (filter.getLastName() != null) {
			criteriaBuilder.append(" LAST_NAME LIKE :LAST_NAME");
			params.addValue("LAST_NAME", filter.getLastName() + "%" );
		}
		
		if (filter.getEmail() != null) {
			if (criteriaBuilder.length() != 0) {
				criteriaBuilder.append(" AND ");
			}
			criteriaBuilder.append(" EMAIL LIKE :EMAIL");
			params.addValue("EMAIL", filter.getEmail() + "%" );
		}
		
		if (criteriaBuilder.length() != 0) {
			sqlb.append(" WHERE ");
			sqlb.append( criteriaBuilder );
		}
		
		sqlb.append(" LIMIT " + filter.getStart() + ", " + filter.getCount());
		
		String sql = sqlb.toString();
		logger.trace(sql);	
		return npJdbcTemplate.query(sql, params, new UserRowMapper());
	}

	@Override
	public int countByFilter(UserFilter filter) throws Exception {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder sqlb = new StringBuilder("SELECT COUNT(*) FROM USER AS U ");
		
		StringBuilder criteriaBuilder = new StringBuilder();
		
		if (filter.getLastName() != null) {
			criteriaBuilder.append(" LAST_NAME LIKE :LAST_NAME");
			params.addValue("LAST_NAME", filter.getLastName() + "%" );
		}
		
		if (filter.getEmail() != null) {
			if (criteriaBuilder.length() != 0) {
				criteriaBuilder.append(" AND ");
			}
			criteriaBuilder.append(" EMAIL LIKE :EMAIL");
			params.addValue("EMAIL", filter.getEmail() + "%" );
		}
		
		if (criteriaBuilder.length() != 0) {
			sqlb.append(" WHERE ");
			sqlb.append( criteriaBuilder );
		}
		
		String sql = sqlb.toString();
		return npJdbcTemplate.queryForObject(sql, params, Integer.class);
	}

}
