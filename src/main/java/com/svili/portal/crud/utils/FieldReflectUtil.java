package com.svili.portal.crud.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

import com.svili.portal.crud.common.DateUtil;
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
			return;
		}
		// Boolean类型字段处理
		if (field.getType().equals(Boolean.class)) {
			if (value instanceof Number) {
				boolean b = NumberUtil.toInt((Number) value) == 1;
				field.set(t, b);
			}else{
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
		//Date类型字段处理
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
	 * 获取目标对象指定的字段值-String类型
	 * <p>
	 * 空值返回null
	 * </p>
	 * <p>
	 * 枚举返回的是ordinal().toString()
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
	@Deprecated
	public static <T> String getFieldStringValue(T t, Field field) throws Exception {
		Object value = getFieldValue(t, field);
		if (value != null) {
			if (field.getType().isEnum()) {
				return Integer.toString(EnumFieldReflectUtil.getFieldEnumOrdinal(t, field));
			}
			if (field.getType().equals(java.util.Date.class)) {
				// yyyy-MM-dd HH:mm:ss.fff
				return DateUtil.toTimestamp((java.util.Date) value).toString();
			}
			if (field.getType().equals(java.sql.Date.class)) {
				// yyyy-MM-dd
				return ((java.sql.Date) value).toString();
			} else {
				return value.toString();
			}
		} else {
			return null;
		}
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

}
