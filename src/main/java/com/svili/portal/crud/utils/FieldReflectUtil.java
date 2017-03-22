package com.svili.portal.crud.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import com.svili.portal.crud.common.NumberUtil;

/**
 * 反射工具类
 * <p>
 * 字段
 * </p>
 * 
 * @author svili
 * @date 2016年11月17日
 *
 */
public class FieldReflectUtil {

	/**
	 * 设定目标对象指定的字段值
	 * 
	 * @param t
	 *            对象
	 * @param field
	 *            字段
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccessException
	 */
	public static <T> void setFieldValue(T t, Field field, Object value) throws Exception {
		ReflectionUtils.makeAccessible(field);
		// Enum类型字段处理
		if (field.getType().isEnum()) {
			// oracle Number类型返回的是BigDecimal
			if (value instanceof Number) {
				int ordinal = NumberUtil.toInt((Number) value);
				EnumFieldReflectUtil.setFieldEnumValueByOrdinal(t, field, ordinal);
			}
			// mysql tinyint(1)返回的是Boolean类型
			if (value instanceof Boolean) {
				int ordinal = (Boolean) value ? 1 : 0;
				EnumFieldReflectUtil.setFieldEnumValueByOrdinal(t, field, ordinal);
			}
			return;
		}
		// Boolean类型字段处理
		if (field.getType().equals(Boolean.class)) {
			if (value instanceof Number) {
				boolean b = NumberUtil.toInt((Number) value) == 1;
				field.set(t, b);
			}
			if (value instanceof Boolean) {
				field.set(t, value);
			}
			return;
		}
		// Number类型字段处理
		if (value instanceof Number) {
			// oracle中Number类型返回的是BigDecimal
			NumberFieldReflectUtil.setFieldNumberValue(t, field, (Number) value);
			return;
		}
		// Date类型字段处理
		if (value instanceof java.util.Date) {
			DateFieldReflectUtil.setFieldDateValue(t, field, (java.util.Date) value);
			return;
		}
		field.set(t, value);
	}

	/**
	 * 获取目标对象指定的字段值
	 * <p>
	 * 空值返回null
	 * </p>
	 * 
	 * @param t
	 *            对象
	 * @param field
	 *            字段
	 * @return value
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccessException
	 */
	public static <T> Object getFieldValue(T t, Field field) throws Exception {
		ReflectionUtils.makeAccessible(field);
		if (field.get(t) == null) {
			return null;
		}
		// Enum类型字段处理
		if (field.getType().isEnum()) {
			return EnumFieldReflectUtil.getFieldEnumOrdinal(t, field);
		}
		return field.get(t);
	}

	/**
	 * 获取Pojo指定注解类型的字段
	 * 
	 * @param clazz
	 *            pojo类-class对象
	 * @param annotationType
	 *            注解类-class对象
	 * @return Field or null
	 */
	public static Field findField(Class<?> clazz, Class<? extends Annotation> annotationType) {
		Class<?> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Field[] fields = searchType.getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(annotationType)) {
					return field;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	/**
	 * 获取所有字段</br>
	 * 暂时无用
	 * 
	 * @param clazz
	 *            class对象
	 * @return
	 */
	@Deprecated
	public static List<Field> getAllField(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		Class<?> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Field[] fields = searchType.getDeclaredFields();
			for (Field field : fields) {
				list.add(field);
			}
			searchType = searchType.getSuperclass();
		}
		return list;
	}

}
