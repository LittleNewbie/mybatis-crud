package com.svili.portal.type;

/**
 * 数据有效性
 * 
 * @author lishiwei
 * @date 2016年8月26日
 *
 */
public enum DataState {
	
	/**
	 * 无效
	 */
	UNEFFECT(0, "无效"),
	
	/**
	 * 有效
	 */
	EFFECT(1, "有效");
	

	private int code;

	private String description;

	private DataState(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public int toCode(){
		return this.code;
	}

}
