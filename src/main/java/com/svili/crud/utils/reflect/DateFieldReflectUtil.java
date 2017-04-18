package com.svili.crud.utils.reflect;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.util.ReflectionUtils;

import com.svili.crud.common.DateUtil;

/**
 * 时间字段处理
 * 
 * @author svili
 * @date 2017年2月18日
 *
 */
public class DateFieldReflectUtil {

	/**
	 * 设置Date类型字段值
	 * 
	 * @param target
	 *            the target object from which to get the field
	 * @param field
	 *            the field to set
	 * @param value
	 *            java.util.Date
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccess
	 */
	public static void setFieldDateValue(Object target, Field field, Object value) throws Exception {

		if (!java.util.Date.class.isAssignableFrom(field.getType())) {
			throw new ReflectionException(target.getClass().getName() + "." + field.getName()
					+ " : field type is not Date, can not convertToDate");
		}

		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}

		if (value == null) {
			field.set(target, null);
			return;
		}

		if (!(value instanceof java.util.Date)) {
			throw new ReflectionException(value + " : is not Date type value , can not convertToDate to field "
					+ target.getClass().getName() + "." + field.getName());
		}

		if (field.getType().equals(java.sql.Date.class)) {
			field.set(target, DateUtil.toSQLDate((java.util.Date) value));
			return;
		}

		if (field.getType().equals(java.sql.Timestamp.class)) {
			field.set(target, DateUtil.toTimestamp((java.util.Date) value));
			return;
		}
		// 其他Date类型 未完成

		field.set(target, value);
	}

}
