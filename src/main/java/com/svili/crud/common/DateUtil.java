package com.svili.crud.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间格式化工具类
 * 
 * @author svili
 * @date 2016年11月17日
 *
 */
public class DateUtil {

	/**
	 * 普通格式化：yyyy-MM-dd
	 * 
	 */
	public static String formatSimple() {
		return formatSimple(System.currentTimeMillis());
	}

	/**
	 * 普通格式化：yyyy-MM-dd
	 * 
	 */
	public static String formatSimple(Date date) {
		return formatSimple(date.getTime());
	}

	/**
	 * 普通格式化：yyyy-MM-dd
	 * 
	 */
	public static String formatSimple(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(time);
	}

	/**
	 * 带时分秒：yyyy-MM-dd HH:mm:ss
	 * 
	 */
	public static String format() {
		return format(System.currentTimeMillis());
	}

	/**
	 * 带时分秒：yyyy-MM-dd HH:mm:ss
	 * 
	 */
	public static String format(Date date) {
		return format(date.getTime());
	}

	/**
	 * 带时分秒：yyyy-MM-dd HH:mm:ss
	 * 
	 */
	public static String format(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(time);
	}

	/**
	 * 自定义
	 *
	 * @param exp
	 *            表达式
	 */
	public static String format(String exp) {
		return format(System.currentTimeMillis(), exp);
	}

	/**
	 * 自定义
	 *
	 * @param exp
	 *            表达式
	 */
	public static String format(Date date, String exp) {
		return format(date.getTime(), exp);
	}

	/**
	 * 自定义
	 *
	 * @param exp
	 *            表达式
	 */
	public static String format(long time, String exp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(exp);
		return simpleDateFormat.format(time);
	}

	/**
	 * 日期
	 */
	public static int getDay() {
		return getDay(System.currentTimeMillis());
	}

	public static int getDay(Date date) {
		return getDay(date.getTime());
	}

	public static int getDay(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 月份
	 */
	public static int getMonth() {
		return getMonth(System.currentTimeMillis());
	}

	public static int getMonth(Date date) {
		return getMonth(date.getTime());
	}

	public static int getMonth(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 年份
	 */
	public static int getYear() {
		return getYear(System.currentTimeMillis());
	}

	public static int getYear(Date date) {
		return getYear(date.getTime());
	}

	public static int getYear(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * sql.Date
	 */
	public static java.sql.Date toSQLDate(Date date) {
		return date instanceof java.sql.Date ? (java.sql.Date) date : toSQLDate(date.getTime());
	}

	public static java.sql.Date toSQLDate(long time) {
		return new java.sql.Date(time);
	}

	public static java.sql.Date getSQLDate() {
		return toSQLDate(System.currentTimeMillis());
	}

	/**
	 * sql.Timestamp
	 */
	public static java.sql.Timestamp toTimestamp(Date date) {
		return date instanceof java.sql.Timestamp ? (java.sql.Timestamp) date : toTimestamp(date.getTime());
	}

	public static java.sql.Timestamp toTimestamp(long time) {
		return new java.sql.Timestamp(time);
	}

	public static java.sql.Timestamp getTimestamp() {
		return toTimestamp(System.currentTimeMillis());
	}

}
