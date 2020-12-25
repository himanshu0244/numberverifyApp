package org.eval.web.numberverifyapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.eval.web.numberverifyapp.model.NumberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class NumberRepository {

	SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public NumberRepository(DataSource dataSource) {
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("contact_number")
				.usingGeneratedKeyColumns("id");
	}

	public Optional<NumberDAO> findByNumber(String number) throws DataAccessException {
		Optional<NumberDAO> result;
		String query = "select * from contact_number where number=?";

		result = Optional.of(jdbcTemplate.queryForObject(query, new Object[] { number }, new NumberEntityRowMapper()));

		return result;
	}

	public List<NumberDAO> findAll() {
		String sql = "select * from contact_number";
		return jdbcTemplate.query(sql, new NumberEntityRowMapper());
	}

	public long save(NumberDAO numberEntity) throws DataAccessException {
		Number newId = null;
		newId = simpleJdbcInsert.executeAndReturnKey(getParams(numberEntity));
		return (long) newId;
	}

	private Map<String, Object> getParams(NumberDAO numberEntity) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("active_ind", 1);
		parameters.put("number", numberEntity.getNumber());
		parameters.put("status", numberEntity.getStatus());
		parameters.put("country_prefix", numberEntity.getCountryPrefix());
		parameters.put("country_code", numberEntity.getCountryCode());
		parameters.put("country_name", numberEntity.getCountryName());
		parameters.put("create_date", numberEntity.getCreateDate());
		parameters.put("created_by", numberEntity.getCreatedBy());
		return parameters;
	}

}
