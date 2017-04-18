package com.svili.crud.common;

public class TypeCastUtil {

	public static Number castToNumber(Object value) throws Exception {
		if (value == null) {
			return null;
		}
		if (TypeIdentifyUtil.isNumberType(value)) {
			return (Number) value;
		}
		if (value instanceof Boolean || value.getClass().equals(Boolean.TYPE)) {
			return Boolean.valueOf((boolean) value) ? 1 : 0;
		}
		if (value instanceof String) {
			// 未完成
		}
		throw new Exception(value.getClass().getName() + " : value is not Number type or Boolean type, can not castToNumber");
	}

	public static Boolean castToBoolean(Object value) throws Exception {
		if (value == null) {
			return null;
		}
		if (TypeIdentifyUtil.isBooleanType(value)) {
			return (Boolean) value;
		}
		if (value instanceof Number) {
			return ((Number) value).intValue() == 1 ? true : false;
		}
		if (value instanceof String) {
			return Boolean.parseBoolean((String) value);
		}

		throw new Exception(value.getClass().getName() + " : value is not Number type or Boolean type, can not castToBoolean");
	}

}
