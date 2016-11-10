package com.svili.portal.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.svili.portal.crud.utils.GeneralMapperReflectUtil;
import com.svili.portal.crud.utils.StringUtil;
import com.svili.portal.dao.GeneralDao;

@Service("crudService")
public class CrudServiceImpl implements CrudServiceInter {

	@Resource(name = "generalDao")
	private GeneralDao dao;

	@Override
	public <T> T selectByPrimaryKey(Class<T> clazz, Integer primaryValue) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);
		List<String> queryColumn = GeneralMapperReflectUtil.getAllColumns(clazz);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("queryColumn", StringUtil.splitListByComma(queryColumn));

		return GeneralMapperReflectUtil.parseToBean(dao.selectByPrimaryKey(param),clazz);
	}

	@Override
	public <T> int insertSelective(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz =  t.getClass();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		Map<String, String> mapping = GeneralMapperReflectUtil.getFieldValueMappingExceptNull(t);

		param.put("tableName", tableName);
		param.put("columnValueMapping", mapping);

		return dao.insertSelective(param);
	}

	@Override
	public <T> int insert(T t) throws Exception {
		return insertSelective(t);
	}

	@Override
	public <T> int deleteByPrimaryKey(Class<T> clazz, String primaryValue) {
		Map<String, Object> param = new HashMap<String, Object>();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);

		return dao.deleteByPrimaryKey(param);
	}

	@Override
	public <T> int updateByPrimaryKey(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz =  t.getClass();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);

		Map<String, String> mapping = GeneralMapperReflectUtil.getAllFieldValueMapping(t);

		String primaryValue = mapping.get(primaryKey);

		mapping.remove(primaryKey);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("columnValueMapping", mapping);

		return dao.updateByPrimaryKey(param);
	}

	@Override
	public <T> int updateByPrimaryKeySelective(T t) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		Class<?> clazz = t.getClass();

		String tableName = GeneralMapperReflectUtil.getTableName(clazz);
		String primaryKey = GeneralMapperReflectUtil.getPrimaryKey(clazz);

		Map<String, String> mapping = GeneralMapperReflectUtil.getFieldValueMappingExceptNull(t);

		String primaryValue = mapping.get(primaryKey);

		mapping.remove(primaryKey);

		param.put("tableName", tableName);
		param.put("primaryKey", primaryKey);
		param.put("primaryValue", primaryValue);
		param.put("columnValueMapping", mapping);

		return dao.updateByPrimaryKey(param);
	}

}
