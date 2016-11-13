package com.svili.portal.crud;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class GeneralDaoProvider {

	@Deprecated
	public String insertSelectiveSql(final Map<String, Object> param) {
		return new SQL() {
			{
				INSERT_INTO((String) param.get("tableName"));

				@SuppressWarnings("unchecked")
				Map<String, String> mapping = (Map<String, String>) param.get("columnValueMapping");
				for (String key : mapping.keySet()) {
					VALUES(key, "#{columnValueMapping." + key + "}");
				}

			}
		}.toString();
	}

	@Deprecated
	public String updateByPrimaryKeySql(final Map<String, Object> param) {
		return new SQL() {
			{
				UPDATE((String) param.get("tableName"));

				@SuppressWarnings("unchecked")
				Map<String, String> mapping = (Map<String, String>) param.get("columnValueMapping");
				for (String key : mapping.keySet()) {
					SET(key + "=" + "#{columnValueMapping." + key + "}");
				}

				WHERE((String) param.get("primaryKey") + "=" + param.get("primaryValue"));

			}
		}.toString();
	}

}
