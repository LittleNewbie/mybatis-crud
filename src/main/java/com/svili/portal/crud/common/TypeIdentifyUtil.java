package com.svili.portal.crud.common;

/**
 * 类型识别工具类
 * 
 * @author svili
 * @data 2017年3月30日
 *
 */
public class TypeIdentifyUtil {

	/**
	 * 布尔类型</br>
	 * null对象返回false
	 * 
	 * @param obj
	 *            待识别的对象
	 * @return
	 */
	public static boolean isBooleanType(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj instanceof Boolean || obj.getClass().equals(Boolean.TYPE)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 数值类型</br>
	 * null对象返回false
	 * 
	 * @param obj
	 *            待识别的对象
	 * @return
	 */
	public static boolean isNumberType(Object obj) {

		if (obj == null) {
			return false;
		}

		if (obj instanceof Number) {
			return true;
		}

		if (obj.getClass().equals(Byte.TYPE)) {
			return true;
		}

		if (obj.getClass().equals(Short.TYPE)) {
			return true;
		}

		if (obj.getClass().equals(Integer.TYPE)) {
			return true;
		}

		if (obj.getClass().equals(Long.TYPE)) {
			return true;
		}

		if (obj.getClass().equals(Float.TYPE)) {
			return true;
		}

		if (obj.getClass().equals(Double.TYPE)) {
			return true;
		}

		return false;
	}
	
}
