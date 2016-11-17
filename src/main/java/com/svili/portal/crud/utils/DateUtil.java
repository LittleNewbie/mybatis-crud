package com.svili.portal.crud.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具类
 * @author svili
 * @date 2016年11月17日
 *
 */
public class DateUtil {
	
	/**
	 * 普通格式化：yyyy-MM-dd
	 * @return
	 */
	public static String format(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(System.currentTimeMillis());
	}
	
	/**
	 * 普通格式化：yyyy-MM-dd
	 * @return
	 */
	public static String format(long time){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(time);
	}
	
	/**
	 * 普通格式化：yyyy-MM-dd
	 * @return
	 */
	public static String format(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 带时分秒：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatAll(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(System.currentTimeMillis());
	}
	
	/**
	 * 带时分秒：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatAll(long time){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(time);
	}
	
	/**
	 * 带时分秒：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatAll(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
}
