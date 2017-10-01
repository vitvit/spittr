package com.vt.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.vt.spring.domain.Spitter;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

	private static final String SQL_UPDATE_SPITTER =
		      "UPDATE spitter set username=?, password=?, firstname=?, lastname=? " +
		      "WHERE id=?";
	
	private static final String SQL_SELECT_SPITTER =
		      "SELECT id, username, password, firstname, lastname " +
		      "FROM spitter ";
	
	@Autowired
	private JdbcOperations jdbcOperations;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Spitter save(Spitter spitter) {
		long spitterId = spitter.getId();
		if(spitterId > 0L) {
			jdbcOperations.update(SQL_UPDATE_SPITTER,
					spitter.getUsername(),
					spitter.getPassword(),
					spitter.getFirstname(),
					spitter.getLastname(),
					spitterId);
			return spitter;
		} else {
			spitterId = insertSpitterAndReturnId(spitter);
			return new Spitter(spitterId,
					spitter.getUsername(),
					spitter.getPassword(),
					spitter.getFirstname(),
					spitter.getLastname());
		}
		
	}
	
	private long insertSpitterAndReturnId(Spitter spitter) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("spitter");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<>();
		args.put("username", spitter.getUsername());
		args.put("password", spitter.getPassword());
		args.put("firstname", spitter.getFirstname());
		args.put("lastname", spitter.getLastname());
		long spitterId = jdbcInsert.executeAndReturnKey(args).longValue();
		return spitterId;
	}

	@Override
	public Spitter findByUsername(String username) {
		try {
			return jdbcOperations.queryForObject(SQL_SELECT_SPITTER + "WHERE username = ?", this::mapRow , username);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	private Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Spitter(rs.getInt("id"),
				   rs.getString("username"),
				   rs.getString("password"),
				   rs.getString("firstname"),
				   rs.getString("lastname"));
	}
}
