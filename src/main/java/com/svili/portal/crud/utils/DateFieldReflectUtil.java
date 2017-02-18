package com.svili.portal.crud.utils;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.util.ReflectionUtils;

import com.svili.portal.crud.common.DateUtil;

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
	 * @param date
	 *            java.util.Date
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccess
	 */
	public static void setFieldDateValue(Object target, Field field, java.util.Date date) throws Exception {
		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}
		if (field.getType().equals(java.sql.Date.class)) {
			field.set(target, DateUtil.toSQLDate(date));
			return;
		}
		if (field.getType().equals(java.sql.Timestamp.class)) {
			field.set(target, DateUtil.toTimestamp(date));
			return;
		}
		if (field.getType().equals(java.util.Date.class)) {
			field.set(target, date);
			return;
		}
		throw new ReflectionException(
				target.getClass().getName() + "." + field.getName() + ":field type is not Date, can not convertToDate");

	}

}
