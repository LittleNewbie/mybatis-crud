package com.svili.crud.mybatis.core;

import java.lang.reflect.Field;

import com.svili.crud.common.PersistentUtil;
import com.svili.crud.utils.reflect.FieldReflectUtil;

/**
 * 工厂方法</br>
 * 待完善
 * 
 * @author svili
 * @data 2017年3月30日
 *
 */
public class SQLColumnFactory {

	public static <T> SQLColumn createSQLColumn(T t, Field field) throws Exception {
		String columnName = PersistentUtil.getColumnName(field);
		Object columnValue = FieldReflectUtil.getFieldValue(t, field);
		Class<?> fieldType = field.getType();
		return createSQLColumn(columnName, columnValue, matchJdbcType(fieldType));
	}

	public static SQLColumn createSQLColumn(String columnName, Object columnValue, String jdbcType) {
		SQLColumn column = new SQLColumn();
		column.setColumnName(columnName);
		column.setColumnValue(columnValue);
		column.setJdbcType(jdbcType);
		return column;
	}

	public static String matchJdbcType(Class<?> fieldType) {
		if (fieldType.isEnum()) {
			// 枚举类型存ordinal
			return "INTEGER";
		}
		if (java.sql.Clob.class.equals(fieldType)) {
			// CLOB未完成
			return "CLOB";
		}
		if (java.sql.Blob.class.equals(fieldType)) {
			// CLOB未完成
			return "BLOB";
		}
		if (Integer.class.equals(fieldType) || Integer.TYPE.equals(fieldType)) {
			return "INTEGER";
		}
		if (Double.class.equals(fieldType) || Double.TYPE.equals(fieldType)) {
			return "DOUBLE";
		}
		if (Float.class.equals(fieldType) || Float.TYPE.equals(fieldType)) {
			return "FLOAT";
		}
		if (java.util.Date.class.isAssignableFrom(fieldType)) {
			return "TIMESTAMP";
		}
		// CLOB BLOB未完成
		return null;
	}

}