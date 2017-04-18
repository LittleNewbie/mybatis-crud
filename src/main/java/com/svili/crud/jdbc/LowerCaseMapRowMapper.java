package com.svili.crud.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class LowerCaseMapRowMapper implements RowMapper<Map<String, Object>> {

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();

		Map<String, Object> map = new LinkedHashMap<>();
		for (int i = 1; i <= columnCount; i++) {
			String key = rsMetaData.getColumnName(i);
			Object valueObj = JdbcUtils.getResultSetValue(rs, i);
			map.put(key.toLowerCase(), valueObj);
		}
		return map;
	}

}
