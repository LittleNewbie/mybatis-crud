package com.svili.crud.utils.reflect;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.util.ReflectionUtils;

import com.svili.crud.common.NumberUtil;
import com.svili.crud.common.TypeCastUtil;

/**
 * 基本数据类型
 * 
 * @author svili
 * @data 2017年4月14日
 *
 */
public class PrimitiveReflectUtil {

	private static final boolean BOOLEAN_DEFAULT_VALUE = false;
	private static final char CHAR_DEFAULT_VALUE = ' ';
	private static final byte BYTE_DEFAULT_VALUE = 0;
	private static final double DOUBLE_DEFAULT_VALUE = 0.0D;
	private static final float FLOAT_DEFAULT_VALUE = 0.0F;
	private static final int INT_DEFAULT_VALUE = 0;
	private static final long LONG_DEFAULT_VALUE = 0L;
	private static final short SHORT_DEFAULT_VALUE = 0;

	/**
	 * 设置基本类型字段值</br>
	 * 如果value==null,赋予基本类型的默认值
	 * 
	 * @param target
	 *            the target object from which to get the field
	 * @param field
	 *            the field to set
	 * @param value
	 *            the value to set
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccess
	 */
	public static void setFieldPrimitiveValue(Object target, Field field, Object value) throws Exception {
		Class<?> fieldType = field.getType();

		if (!fieldType.isPrimitive()) {
			throw new ReflectionException(target.getClass().getName() + "." + field.getName()
					+ " : field type is not primitive, can not convertToPrimitive");
		}

		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}

		// 空值赋默认值
		if (value == null) {
			field.set(target, getPrimitiveDefaultValue(fieldType));
			return;
		}

		if (fieldType.equals(boolean.class)) {
			boolean b = TypeCastUtil.castToBoolean(value);
			field.set(target, b);
			return;
		}

		if (fieldType.equals(char.class)) {
			field.set(target, (char) value);
			return;
		}

		Number number = (Number) value;

		if (fieldType.equals(byte.class)) {
			field.set(target, NumberUtil.toByte(number));
			return;
		}
		if (fieldType.equals(double.class)) {
			field.set(target, NumberUtil.toDouble(number));
			return;
		}
		if (fieldType.equals(float.class)) {
			field.set(target, NumberUtil.toFloat(number));
			return;
		}
		if (fieldType.equals(int.class)) {
			field.set(target, NumberUtil.toInt(number));
			return;
		}
		if (fieldType.equals(long.class)) {
			field.set(target, NumberUtil.toLong(number));
			return;
		}
		if (fieldType.equals(short.class)) {
			field.set(target, NumberUtil.toShort(number));
			return;
		}

	}

	public static Object getPrimitiveDefaultValue(Class<?> type) {
		/*
		 * if (!type.isPrimitive()) { throw new ReflectionException(
		 * type.getName() +
		 * " : type is not primitive, can not getPrimitiveDefaultValue"); }
		 */
		if (type.equals(boolean.class)) {
			return BOOLEAN_DEFAULT_VALUE;
		}
		if (type.equals(char.class)) {
			return CHAR_DEFAULT_VALUE;
		}
		if (type.equals(byte.class)) {
			return BYTE_DEFAULT_VALUE;
		}
		if (type.equals(double.class)) {
			return DOUBLE_DEFAULT_VALUE;
		}
		if (type.equals(float.class)) {
			return FLOAT_DEFAULT_VALUE;
		}
		if (type.equals(int.class)) {
			return INT_DEFAULT_VALUE;
		}
		if (type.equals(long.class)) {
			return LONG_DEFAULT_VALUE;
		}
		if (type.equals(short.class)) {
			return SHORT_DEFAULT_VALUE;
		}
		throw new ReflectionException(type.getName() + " : type is not primitive, can not getPrimitiveDefaultValue");
	}

}
