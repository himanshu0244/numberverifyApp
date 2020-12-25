package org.eval.web.numberverifyapp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eval.web.numberverifyapp.model.NumberDAO;
import org.springframework.jdbc.core.RowMapper;

public class NumberEntityRowMapper implements RowMapper<NumberDAO> {

	@Override
	public NumberDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
		NumberDAO numberEntity = new NumberDAO();
		numberEntity.setId(rowNum);
		numberEntity.setNumber(rs.getString("number"));
		numberEntity.setStatus(rs.getString("status"));
		numberEntity.setCountryPrefix(rs.getString("country_prefix"));
		numberEntity.setCountryCode(rs.getString("country_code"));
		numberEntity.setCountryName(rs.getString("country_name"));
		return numberEntity;
	}

}
