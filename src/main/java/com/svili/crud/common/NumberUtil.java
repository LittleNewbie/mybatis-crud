package com.svili.crud.common;

import java.math.BigDecimal;

/**
 * Number格式化工具类
 * 
 * @author svili
 * @date 2017年2月18日
 *
 */
public class NumberUtil {

	public static byte toByte(Number number) {
		return number.byteValue();
	}

	public static double toDouble(Number number) {
		return number.doubleValue();
	}

	public static float toFloat(Number number) {
		return number.floatValue();
	}

	public static int toInt(Number number) {
		return number.intValue();
	}

	public static long toLong(Number number) {
		return number.longValue();
	}

	public static short toShort(Number number) {
		return number.shortValue();
	}

	public static BigDecimal toBigDecimal(Number number) {
		if (number instanceof BigDecimal) {
			return (BigDecimal) number;
		} else {
			return new BigDecimal(number.doubleValue());
		}
	}

}
