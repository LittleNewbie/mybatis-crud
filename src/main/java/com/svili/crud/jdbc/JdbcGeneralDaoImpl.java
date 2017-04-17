package com.svili.crud.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.svili.crud.mybatis.common.PersistentUtil;
import com.svili.crud.utils.mapper.GeneralMapperUtil;

@Repository("jdbcGeneralDao")
public class JdbcGeneralDaoImpl implements JdbcGeneralDao {

	@Resource(name = "namedparamJdbcTemplate")
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public <T> T selectByPrimaryKey(Class<T> clazz, Object primaryValue) {
		String tableName = PersistentUtil.getTableName(clazz);
		String primaryKey = PersistentUtil.getPrimaryKey(clazz);

		String queryColumns = GeneralMapperUtil.getAllColumnNames(clazz);

		String condition = primaryKey + " = " + " :" + primaryKey;

		String SQL = "SELECT " + queryColumns + " FROM " + tableName + " WHERE " + condition;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(primaryKey, primaryValue);

		GeneralMapper<T> rowMapper = new GeneralMapper<T>(clazz);

		return jdbcTemplate.queryForObject(SQL.toString(), paramMap, rowMapper);
	}

	@Override
	public <T> int insert(T t) throws Exception {

		String tableName = PersistentUtil.getTableName(t.getClass());

		Map<String, Object> columnValueMapper = GeneralMapperUtil.getAllColumnValueMapping(t);

		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();

		for (String columnName : columnValueMapper.keySet()) {
			columns.append(",").append(columnName);
			values.append(",").append(":").append(columnName);
		}

		String SQL = "INSERT INTO " + tableName + " (" + columns.substring(1) + ") VALUES ( " + values.substring(1)
				+ ")";

		return jdbcTemplate.update(SQL, columnValueMapper);
	}

	@Override
	public <T> int insertSelective(T t) throws Exception {

		String tableName = PersistentUtil.getTableName(t.getClass());

		Map<String, Object> columnValueMapper = GeneralMapperUtil.getColumnValueMappingExceptNull(t);

		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();

		for (String columnName : columnValueMapper.keySet()) {
			columns.append(",").append(columnName);
			values.append(",").append(":").append(columnName);
		}

		String SQL = "INSERT INTO " + tableName + " (" + columns.substring(1) + ") VALUES ( " + values.substring(1)
				+ ")";

		return jdbcTemplate.update(SQL, columnValueMapper);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> int insertBatch(List<T> list) throws Exception {

		List<Map<String, Object>> batchValues = new ArrayList<Map<String, Object>>(list.size() + 1);

		if (list == null || list.isEmpty()) {
			return 0;
		}

		Class<?> clazz = list.get(0).getClass();
		String tableName = PersistentUtil.getTableName(clazz);

		List<String> columnNameList = GeneralMapperUtil.getAllColumnNameList(clazz);

		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();

		for (String columnName : columnNameList) {
			columns.append(",").append(columnName);
			values.append(",").append(":").append(columnName);
		}

		for (T t : list) {
			Map<String, Object> columnValueMapper = GeneralMapperUtil.getAllColumnValueMapping(t);
			batchValues.add(columnValueMapper);
		}

		String SQL = "INSERT INTO " + tableName + " (" + columns.substring(1) + ") VALUES ( " + values.substring(1)
				+ ")";

		return jdbcTemplate.batchUpdate(SQL, batchValues.toArray(new Map[list.size()])).length;
	}

	@Override
	public <T> int deleteByPrimaryKey(Class<T> clazz, Object primaryValue) {
		String tableName = PersistentUtil.getTableName(clazz);
		String primaryKey = PersistentUtil.getPrimaryKey(clazz);

		String condition = primaryKey + " = " + " :" + primaryKey;

		String SQL = "DELETE FROM " + tableName + " WHERE " + condition;

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(primaryKey, primaryValue);

		return jdbcTemplate.update(SQL, paramMap);
	}

	@Override
	public <T> int updateByPrimaryKey(T t) throws Exception {
		String tableName = PersistentUtil.getTableName(t.getClass());
		String primaryKey = PersistentUtil.getPrimaryKey(t.getClass());

		String condition = primaryKey + " = " + " :" + primaryKey;

		Map<String, Object> columnValueMapper = GeneralMapperUtil.getAllColumnValueMapping(t);

		StringBuilder SETS = new StringBuilder();
		for (String columnName : columnValueMapper.keySet()) {
			SETS.append(",").append(columnName).append(" = :").append(columnName);
		}

		String SQL = "UPDATE " + tableName + " SET " + SETS.substring(1).toString() + condition;

		return jdbcTemplate.update(SQL, columnValueMapper);
	}

	@Override
	public <T> int updateSelectiveByPrimaryKey(T t) throws Exception {
		String tableName = PersistentUtil.getTableName(t.getClass());
		String primaryKey = PersistentUtil.getPrimaryKey(t.getClass());

		String condition = primaryKey + " = " + " :" + primaryKey;

		Map<String, Object> columnValueMapper = GeneralMapperUtil.getAllColumnValueMapping(t);

		StringBuilder SETS = new StringBuilder();

		for (String columnName : columnValueMapper.keySet()) {
			SETS.append(",").append(columnName).append(" = :").append(columnName);
		}

		String SQL = "UPDATE " + tableName + " SET " + SETS.substring(1).toString() + condition;

		return jdbcTemplate.update(SQL, columnValueMapper);
	}

}
