package com.svili.crud.mybatis.core;

import java.util.List;
import java.util.Map;

public class GeneralQueryParam {
	
	/**
	 * 表对应的pojo类
	 */
	private Class<?> queryClazz;
	
	/**
	 * 查询列
	 */
	private List<String> queryColumn;
	
	/**
	 * 查询条件 where 表达式 
	 * <p>不要写where</p>
	 * <p>传入的参数格式为:#{conditionParam.paramName}</p>
	 */
	private String conditionExp;
	
	/**
	 * 查询条件 where 表达式中的参数集
	 * <p>key:paramName</p>
	 */
	private Map<String, Object> conditionParam;
	
	/**
	 * 每页的数据条数
	 * <p>分页查询使用</p>
	 */
	private Integer pageSize;
	
	/**
	 * 页码
	 * <p>分页查询使用</p>
	 */
	private Integer pageNo;
	
	/**
	 * order by 表达式
	 * <p>格式:columnName asc|desc</p>
	 * <p>多个排序字段用逗号分隔</p>
	 */
	private String orderExp;

	public GeneralQueryParam() {
	}

	public Class<?> getQueryClazz() {
		return queryClazz;
	}

	public void setQueryClazz(Class<?> queryClazz) {
		this.queryClazz = queryClazz;
	}

	public List<String> getQueryColumn() {
		return queryColumn;
	}

	public void setQueryColumn(List<String> queryColumn) {
		this.queryColumn = queryColumn;
	}

	public String getConditionExp() {
		return conditionExp;
	}

	public void setConditionExp(String conditionExp) {
		this.conditionExp = conditionExp;
	}

	public Map<String, Object> getConditionParam() {
		return conditionParam;
	}

	public void setConditionParam(Map<String, Object> conditionParam) {
		this.conditionParam = conditionParam;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrderExp() {
		return orderExp;
	}

	public void setOrderExp(String orderExp) {
		this.orderExp = orderExp;
	}
	
}
