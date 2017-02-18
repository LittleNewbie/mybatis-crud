package com.svili.portal.crud.utils;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.util.ReflectionUtils;

import com.svili.portal.crud.common.NumberUtil;

/**
 * 数值字段处理
 * 
 * @author svili
 * @date 2017年2月18日
 *
 */
public class NumberFieldReflectUtil {

	/**
	 * 设置Date类型字段值
	 * 
	 * @param target
	 *            the target object from which to get the field
	 * @param field
	 *            the field to set
	 * @param number
	 *            java.lang.Number
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccess
	 */
	public static void setFieldNumberValue(Object target, Field field, java.lang.Number number) throws Exception {
		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}
		if (field.getType().equals(java.lang.Byte.class)) {
			field.set(target, NumberUtil.toByte(number));
			return;
		}
		if (field.getType().equals(java.lang.Double.class)) {
			field.set(target, NumberUtil.toDouble(number));
			return;
		}
		if (field.getType().equals(java.lang.Float.class)) {
			field.set(target, NumberUtil.toFloat(number));
			return;
		}
		if (field.getType().equals(java.lang.Integer.class)) {
			field.set(target, NumberUtil.toInt(number));
			return;
		}
		if (field.getType().equals(java.lang.Long.class)) {
			field.set(target, NumberUtil.toLong(number));
			return;
		}
		if (field.getType().equals(java.lang.Short.class)) {
			field.set(target, NumberUtil.toShort(number));
			return;
		}
		if (field.getType().equals(java.math.BigDecimal.class)) {
			field.set(target, NumberUtil.toBigDecimal(number));
			return;
		}
		throw new ReflectionException(target.getClass().getName() + "." + field.getName()
				+ ":field type is not Number, can not convertToNumber");

	}
}
