package com.svili.crud.utils.reflect;

import java.lang.reflect.Field;

import org.apache.ibatis.reflection.ReflectionException;
import org.springframework.util.ReflectionUtils;

/**
 * 枚举类型字段处理
 * 
 * @author svili
 * @date 2016年11月10日
 *
 */
public class EnumFieldReflectUtil {

	/**
	 * 获取枚举类型的字段值-ordinal
	 * 
	 * @param target
	 *            the target object from which to get the field
	 * @param field
	 *            the field to get
	 * @return enum.ordinal
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccess
	 */
	@SuppressWarnings("rawtypes")
	public static int getFieldEnumOrdinal(Object target, Field field) throws Exception {
		
		if (!field.getType().isEnum()) {
			throw new ReflectionException(target.getClass().getName() + "." + field.getName()
					+ ":field type is not Enum, can not convertToEnum");
		}
		
		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}
		
		return ((Enum) field.get(target)).ordinal();
	}

	/**
	 * 
	 * 设置枚举类型的字段值
	 * 
	 * @param target
	 *            the target object from which to get the field
	 * @param field
	 *            the field to set
	 * @param ordinal
	 *            enum.ordinal
	 * @throws Exception
	 *             IllegalArgumentException, IllegalAccess
	 */
	@SuppressWarnings("rawtypes")
	public static void setFieldEnumValueByOrdinal(Object target, Field field, Integer ordinal) throws Exception {
		if (!field.getType().isEnum()) {
			throw new ReflectionException(target.getClass().getName() + "." + field.getName()
					+ " : field type is not Enum, can not convertToEnum");
		}
		
		if (!field.isAccessible()) {
			ReflectionUtils.makeAccessible(field);
		}
		
		if(ordinal == null){
			field.set(target, null);
			return;
		}

		Enum[] enumObjs = (Enum[]) (field.getType()).getEnumConstants();
		for (Enum enumObj : enumObjs) {
			if (enumObj.ordinal() == ordinal) {
				field.set(target, enumObj);
			}
		}

	}

}
