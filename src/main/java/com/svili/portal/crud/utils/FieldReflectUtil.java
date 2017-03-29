package com.svili.portal.crud.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import com.svili.portal.crud.common.NumberUtil;
import com.svili.portal.crud.common.TypeCastUtil;

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
	 * 设定目标对象指定的字段值</br>
	 * oracle Number类型返回的是BigDecimal</br>
	 * mysql tinyint(1)返回的是Boolean类型
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
		
		//空值处理
		if(value == null){
			field.set(t, null);
			return;
		}
		
		//基本数据类型 未完成
		if(field.getType().isPrimitive()){
			
		}
		
		// Enum类型字段处理
		if (field.getType().isEnum()) {
			//Enum类型的字段在数据库中存储其ordinal
			Number number = TypeCastUtil.castToNumber(value);
			int ordinal = NumberUtil.toInt(number);
			EnumFieldReflectUtil.setFieldEnumValueByOrdinal(t, field, ordinal);
			return;
		}
		
		// Boolean类型字段处理
		if (field.getType().equals(Boolean.class)) {
			boolean b = TypeCastUtil.castToBoolean(value);
			field.set(t, b);
			return;
		}
		
		// Number类型字段处理
		if (Number.class.isAssignableFrom(field.getType())) {
			// oracle中Number类型返回的是BigDecimal
			NumberFieldReflectUtil.setFieldNumberValue(t, field, (Number) value);
			return;
		}
		
		// Date类型字段处理
		if (java.util.Date.class.isAssignableFrom(field.getType())) {
			DateFieldReflectUtil.setFieldDateValue(t, field, value);
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
	 * 获取class类中指定注解类型的field对象
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
