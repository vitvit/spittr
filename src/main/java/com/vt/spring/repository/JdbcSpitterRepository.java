package com.vt.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.vt.spring.domain.Spitter;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {

	private static final String SQL_INSERT_SPITTER =
		      "INSERT INTO spitter (username, password, firstname, lastname) " +
		      "values (?,?,?,?)";
	
	private static final String SQL_SELECT_SPITTER =
		      "SELECT id, username, password, firstname, lastname " +
		      "FROM spitter ";
	
	@Autowired
	JdbcOperations jdbcOperations;
	
	@Override
	public Spitter save(Spitter spitter) {
		jdbcOperations.update(SQL_INSERT_SPITTER,
				spitter.getUsername(),
				spitter.getPassword(),
				spitter.getFirstname(),
				spitter.getLastname());
		return spitter;
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
