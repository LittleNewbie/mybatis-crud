package com.svili.portal.crud;

import java.util.List;
import java.util.Map;

/**
 * 通用Crud 数据访问层接口
 * 
 * @author svili
 * @date 2016年11月11日
 *
 */
public interface CrudServiceInter {

	/**
	 * 根据主键查询
	 * 
	 * @param <T>
	 *            pojo类
	 * @param clazz
	 *            pojo类-class对象
	 * @param primaryValue
	 *            主键值
	 * @return pojo对象
	 * @throws Exception
	 */
	<T> T selectByPrimaryKey(Class<T> clazz, Object primaryValue) throws Exception;

	/**
	 * 插入数据</br>
	 * 包括null值
	 * 
	 * @param <T>
	 *            pojo类
	 * @param t
	 *            pojo对象
	 * @return 数据条数
	 * @throws Exception
	 */
	<T> int insert(T t) throws Exception;

	/**
	 * 插入数据</br>
	 * 不包括null值
	 * 
	 * @param <T>
	 *            pojo类
	 * @param t
	 *            pojo对象
	 * @return 数据条数
	 * @throws Exception
	 */
	<T> int insertSelective(T t) throws Exception;

	/**
	 * 批量插入</br>
	 * 包括null值
	 * 
	 * @param list
	 *            数据集
	 * @return 数据
	 * @throws Exception
	 */
	<T> int insertBatch(List<T> list) throws Exception;

	/**
	 * 删除 </br>
	 * 根据主键删除
	 * 
	 * @param <T>
	 *            pojo类
	 * @param clazz
	 *            pojo类-class对象
	 * @param primaryValue
	 *            主键值
	 * @return 数据条数
	 */
	<T> int deleteByPrimaryKey(Class<T> clazz, Object primaryValue);

	/**
	 * 删除 </br>
	 * 根据条件删除
	 * 
	 * @param <T>
	 *            pojo类
	 * @param clazz
	 *            pojo类-class对象
	 * @param conditionExp
	 *            查询条件 where 表达式 @see GeneralQueryParam
	 * 
	 * @param conditionParam
	 *            查询条件 where 表达式中的参数集 @see GeneralQueryParam
	 * @return 数据条数
	 */
	<T> int deleteByCondition(Class<T> clazz, String conditionExp, Map<String, Object> conditionParam);

	/**
	 * 更新</br>
	 * 根据主键更新</br>
	 * 更新pojo的所有字段，包括空值(null值)字段
	 * 
	 * @param <T>
	 *            pojo类
	 * @param t
	 *            pojo对象
	 * @return 数据条数
	 * @throws Exception
	 */
	<T> int updateByPrimaryKey(T t) throws Exception;

	/**
	 * 更新</br>
	 * 根据主键更新</br>
	 * 更新pojo的非空字段
	 * 
	 * @param <T>
	 *            pojo类
	 * @param t
	 *            pojo对象
	 * @return 数据条数
	 * @throws Exception
	 */
	<T> int updateByPrimaryKeySelective(T t) throws Exception;

	/**
	 * 更新</br>
	 * 根据条件更新</br>
	 * 更新pojo的指定字段集
	 * 
	 * @param clazz
	 *            pojo类-class对象
	 * @param columnValueMapping
	 *            需要更新的列名-值,注意列名必须与数据库中列名一致
	 * @param conditionExp
	 *            查询条件 where 表达式 @see GeneralQueryParam
	 * 
	 * @param conditionParam
	 *            查询条件 where 表达式中的参数集 @see GeneralQueryParam
	 * @return 数据条数
	 * @throws Exception
	 */
	<T> int updateByConditionSelective(Class<T> clazz, Map<String, Object> columnValueMapping, String conditionExp,
			Map<String, Object> conditionParam) throws Exception;

	/**
	 * 高级查询
	 * 
	 * @param clazz
	 *            pojo类-class对象
	 * @param queryParam
	 *            查询参数
	 * @return
	 * @throws Exception
	 */
	<T> List<T> selectAdvanced(Class<T> clazz, GeneralQueryParam queryParam) throws Exception;

	/**
	 * 高级查询,指定返回列
	 * 
	 * @param clazz
	 *            pojo类-class对象
	 * @param queryParam
	 *            查询参数
	 * @return
	 * @throws Exception
	 */
	<T> List<Map<String, Object>> selectAdvancedByColumn(Class<T> clazz, GeneralQueryParam queryParam) throws Exception;

}
