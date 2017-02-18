package com.svili.portal.crud;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.jdbc.SQL;

public class GeneralDaoProvider {

	@Deprecated
	public String insertSelectiveSql( final Map<String, Object> param) {
		return new SQL() {
			{
				INSERT_INTO((String) param.get("tableName"));

				@SuppressWarnings("unchecked")
				Map<String, Object> mapping = (Map<String, Object>) param.get("columnValueMapping");
				for (Entry<String,Object> entry : mapping.entrySet()) {
					if(entry.getValue() instanceof java.util.Date){
						VALUES(entry.getKey(), "#{columnValueMapping." + entry.getKey() + ",jdbcType=TIMESTAMP}");
						break;
					}
					VALUES(entry.getKey(), "#{columnValueMapping." + entry.getKey() + "}");
				}
				/*for (String key : mapping.keySet()) {
					if("time".equals(key)){
						VALUES(key, "#{columnValueMapping." + key + ",jdbcType=TIMESTAMP}");
						break;
					}
					VALUES(key, "#{columnValueMapping." + key + "}");
				}*/

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
