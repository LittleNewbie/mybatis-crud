package com.svili.crud.jdbc;

import java.util.List;

/**
 * 通用Crud 数据访问层接口
 * 
 * @author svili
 * @date 2016年11月11日
 *
 */
public interface JdbcGeneralDao {

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
	<T> int updateSelectiveByPrimaryKey(T t) throws Exception;

}
