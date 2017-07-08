/**
 * 
 */
package com.bigroi.shop.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.bigroi.shop.dao.UserDao;
import com.bigroi.shop.model.User;

/**
 * @author Alexander Medvedev
 *
 */
public class UserDaoImpl implements UserDao {
	
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
		String sql = null;
		if (user.getId() == null) {
			sql = "INSERT INTO USER(FIRST_NAME, LAST_NAME, EMAIL, PHONE) VALUES (:FIRST_NAME, :LAST_NAME, :EMAIL, :PHONE)"; 
		} else {
			sql = "UPDATE USER SET FIRST_NAME=:FIRST_NAME, LAST_NAME=:LAST_NAME, EMAIL=:EMAIL, PHONE=:PHONE WHERE USER_ID=:USER_ID";
		}
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("FIRST_NAME", user.getFirstName())
			.addValue("LAST_NAME", user.getLastName())
			.addValue("EMAIL", user.getEmail())
			.addValue("PHONE", user.getPhone())
			.addValue("USER_ID", user.getId());
		
		npJdbcTemplate.update(sql, params);
	}

	@Override
	public User findById(int userId) throws Exception {
		String sql = "SELECT U.USER_ID, U.FIRST_NAME, U.LAST_NAME, U.EMAIL, U.CRTD_TMS, U.UPDT_TMS, U.PHONE FROM USER AS U WHERE USER_ID=:USER_ID";
		SqlParameterSource params = new MapSqlParameterSource().addValue("USER_ID", userId);	
		return npJdbcTemplate.queryForObject(sql, params, new UserRowMapper());
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

}
