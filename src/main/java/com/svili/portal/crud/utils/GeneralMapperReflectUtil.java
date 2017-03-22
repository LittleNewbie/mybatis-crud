package com.svili.portal.crud.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.svili.portal.crud.common.PersistentUtil;

/**
 * 映射工具类
 * 
 * @author svili
 * @date 2016年11月8日
 *
 */
public class GeneralMapperReflectUtil {

	/**
	 * 获取pojo对应table 的所有列名</br>
	 * 下划线风格
	 * 
	 * @param clazz
	 *            pojo类-class对象
	 * @return columnNames
	 */
	public static List<String> getAllColumns(Class<?> clazz) {
		List<String> columnNames = new ArrayList<String>();
		List<Field> fields = PersistentUtil.getPersistentFields(clazz);
		for (Field field : fields) {
			columnNames.add(PersistentUtil.getColumnName(field));
		}
		return columnNames;
	}

	/**
	 * 获取pojo对应table除主键外的所有列名</br>
	 * 列名为下划线风格
	 * 
	 * @param clazz
	 *            pojo类-class对象
	 * @return columnNames
	 */
	public static List<String> getAllColumnsExceptPrimaryKey(Class<?> clazz) {
		List<String> columnNames = new ArrayList<String>();

		Field primaryField = PersistentUtil.getPrimaryFieldNotCareNull(clazz);

		List<Field> fields = PersistentUtil.getPersistentFields(clazz);

		for (Field field : fields) {
			if (primaryField != null && !field.equals(primaryField)) {
				columnNames.add(PersistentUtil.getColumnName(field));
			}
		}
		return columnNames;
	}

	/**
	 * 获取pojo除空值外的所有字段名-字段值mapping
	 * <p>
	 * 字段名为下划线风格
	 * </p>
	 * 
	 * @param <T>
	 *            pojo类
	 * 
	 * @param t
	 *            pojo对象
	 * @return columnName-value
	 * @throws Exception
	 */
	public static <T> Map<String, Object> getColumnValueMappingExceptNull(T t) throws Exception {
		Map<String, Object> mapping = new LinkedHashMap<String, Object>();

		List<Field> fields = PersistentUtil.getPersistentFields(t.getClass());

		for (Field field : fields) {
			Object fieldValue = FieldReflectUtil.getFieldValue(t, field);
			if (fieldValue != null) {
				mapping.put(PersistentUtil.getColumnName(field), fieldValue);
			}
		}

		return mapping;
	}

	/**
	 * 获取pojo对应table的所有列名-字段值mapping</br>
	 * 列名为下划线风格</br>
	 * 
	 * @param <T>
	 *            pojo类
	 * 
	 * @param t
	 *            pojo对象
	 * @return columnName-value
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccessException
	 * @see FieldReflectUtil.getFieldValue
	 */
	public static <T> Map<String, Object> getAllColumnValueMapping(T t) throws Exception {
		Map<String, Object> mapping = new LinkedHashMap<String, Object>();

		List<Field> fields = PersistentUtil.getPersistentFields(t.getClass());

		for (Field field : fields) {
			Object fieldValue = FieldReflectUtil.getFieldValue(t, field);
			mapping.put(PersistentUtil.getColumnName(field), fieldValue);
		}
		return mapping;
	}

	/**
	 * 获取pojo对应table的 列名-字段值mapping</br>
	 * 列名为下划线风格</br>
	 * 
	 * @param <T>
	 *            pojo类
	 * 
	 * @param t
	 *            pojo对象
	 * @param isContainsPrimaryKey
	 *            是否包含主键
	 * @param isContainsNullValue
	 *            是否包含空值
	 * @return columnName-value
	 * @throws Exception
	 */
	public static <T> Map<String, Object> getColumnValueMapping(T t, boolean isContainsPrimaryKey,
			boolean isContainsNullValue) throws Exception {
		Map<String, Object> mapping = new LinkedHashMap<String, Object>();

		List<Field> fields = PersistentUtil.getPersistentFields(t.getClass());

		// 主键字段
		Field primaryField = PersistentUtil.getPrimaryField(t.getClass());

		if (!isContainsPrimaryKey && primaryField != null) {
			// 不包含主键字段，移除
			fields.remove(primaryField);
		}

		for (Field field : fields) {
			// 字段值
			Object fieldValue = FieldReflectUtil.getFieldValue(t, field);

			if (fieldValue == null) {
				// 空值
				if (isContainsNullValue) {
					// 包含空值 添加至结果集
					mapping.put(PersistentUtil.getColumnName(field), fieldValue);
				}

			} else {
				// 非空值 直接添加至结果集
				mapping.put(PersistentUtil.getColumnName(field), fieldValue);
			}

		}

		return mapping;
	}

	/**
	 * 列名-值 键值对转为pojo</br>
	 * 若mapping为空,则返回null对象</br>
	 * 
	 * @param mapping
	 *            columnName-value mapping
	 * @param clazz
	 *            待转换的pojo类-class对象
	 * @throws Exception
	 *             InstantiationException, IllegalAccessException
	 */
	public static <T> T parseToBean(Map<String, Object> mapping, Class<T> clazz) throws Exception {
		if (mapping == null || mapping.size() == 0) {
			return null;
		}

		T t = clazz.newInstance();
		for (Entry<String, Object> entry : mapping.entrySet()) {

			// oracle返回的columnName为大写
			String columnName = entry.getKey().toLowerCase();

			Field field = PersistentUtil.getFieldByColumnName(clazz, columnName);

			if (field != null) {
				FieldReflectUtil.setFieldValue(t, field, entry.getValue());
			}
		}
		return t;
	}

}
