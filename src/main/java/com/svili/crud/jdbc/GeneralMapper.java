package com.svili.crud.jdbc;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.svili.crud.mybatis.common.PersistentUtil;

public class GeneralMapper<T> implements RowMapper<T> {

	private Class<T> clazz;

	public GeneralMapper(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {

		T t;
		try {
			t = clazz.newInstance();
		} catch (Exception e) {
			throw new SQLException(e);
		}

		List<Field> fields = PersistentUtil.getPersistentFields(clazz);

		for (Field field : fields) {

			try {
				ResulsetMapperUtil.setFieldValue(t, field, rs);
			} catch (Exception e) {
				throw new SQLException(e);
			}
			
		}

		return t;
	}

}
