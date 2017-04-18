package com.svili.crud.jdbc;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;

import org.springframework.util.ReflectionUtils;

import com.svili.crud.common.PersistentUtil;
import com.svili.crud.utils.reflect.DateFieldReflectUtil;
import com.svili.crud.utils.reflect.EnumFieldReflectUtil;
import com.svili.crud.utils.reflect.NumberFieldReflectUtil;
import com.svili.crud.utils.reflect.PrimitiveReflectUtil;

/**
 * JdbcTemplate RowMapper工具类
 * 
 * @author svili
 * @data 2017年4月17日
 *
 */
public class ResulsetMapperUtil {

	public static <T> void setFieldValue(T target, Field field, ResultSet rs) throws Exception {
		//oracle 返回的字段名大写
		String columnName = PersistentUtil.getColumnName(field);

		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}

		Class<?> fieldType = field.getType();

		// 基本数据类型
		if (fieldType.isPrimitive()) {
			Object value = rs.getObject(columnName);
			PrimitiveReflectUtil.setFieldPrimitiveValue(target, field, value);
		}

		// 空值
		if (rs.getObject(columnName) == null) {
			field.set(target, null);
			return;
		}

		// Enum类型字段处理
		if (field.getType().isEnum()) {
			// Enum类型的字段在数据库中存储其ordinal
			int ordinal = rs.getInt(columnName);
			EnumFieldReflectUtil.setFieldEnumValueByOrdinal(target, field, ordinal);
			return;
		}

		// Boolean类型字段处理
		if (field.getType().equals(Boolean.class)) {
			boolean b = rs.getBoolean(columnName);
			field.set(target, b);
			return;
		}

		// Number类型字段处理
		if (Number.class.isAssignableFrom(field.getType())) {
			// oracle中Number类型返回的是BigDecimal
			BigDecimal value = rs.getBigDecimal(columnName);
			NumberFieldReflectUtil.setFieldNumberValue(target, field, value);
			return;
		}

		// Date类型字段处理
		if (java.util.Date.class.isAssignableFrom(field.getType())) {
			java.sql.Timestamp value = rs.getTimestamp(columnName);
			DateFieldReflectUtil.setFieldDateValue(target, field, value);
			return;
		}

	}

}
