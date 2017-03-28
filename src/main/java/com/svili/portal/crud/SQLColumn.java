package com.svili.portal.crud;

/**
 * SQL字段对象</br>
 * java对象与jdbc转换</br>
 * Mybatis-insert update中Oracle.data类型对应的Java对象需要声明jdbcType=TIMESTAMP
 * 
 * @author svili
 * @data 2017年3月27日
 *
 */
public class SQLColumn {

	private String columnName;

	private Object columnValue;

	private String jdbcType;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Object getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(Object columnValue) {
		this.columnValue = columnValue;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String adaptorJdpcType() {
		if (this.columnValue == null) {
			return null;
		}
		if (this.columnValue instanceof java.util.Date) {
			this.jdbcType = "TIMESTAMP";
		}
		return this.jdbcType;
	}

}
