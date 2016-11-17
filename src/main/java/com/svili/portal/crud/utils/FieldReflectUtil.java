package com.svili.portal.crud.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

/**
 * 反射工具类
 * <p>字段</p>
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
		if (field.getType().isEnum()) {
			EnumFieldReflectUtil.setFieldEnumValueByOrdinal(t, field, (int) value);
		} else {
			field.set(t, value);
		}
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
		if (field.get(t) != null) {
			return field.get(t);
		} else {
			return null;
		}
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
	public static <T> String getFieldStringValue(T t, Field field) throws Exception {
		Object value = getFieldValue(t, field);
		if (value != null) {
			if (field.getType().isEnum()) {
				return Integer.toString(EnumFieldReflectUtil.getFieldEnumOrdinal(t, field));
			} if(field.getType().equals(java.util.Date.class)){
				//yyyy-MM-dd HH:mm:ss
				return DateUtil.formatAll((java.util.Date)value);
			}if(field.getType().equals(java.sql.Date.class)){
				//yyyy-MM-dd
				return DateUtil.format((java.sql.Date)value);
			}else {
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
