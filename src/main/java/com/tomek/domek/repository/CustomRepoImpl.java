package com.tomek.domek.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CustomRepoImpl implements CustomRepo {

	@Autowired
	EntityManager em;
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public int deleteTokenByUserId(Long id) {
		
		return jdbc.update("delete from password_reset_token where userid=?", new Object[] {id} );
		
	}

}
