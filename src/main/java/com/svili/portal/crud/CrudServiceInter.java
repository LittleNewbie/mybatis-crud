package com.svili.portal.crud;

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
	<T> T selectByPrimaryKey(Class<T> clazz, Integer primaryValue) throws Exception;

	/**
	 * 插入数据
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
	 * 插入数据
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
	 * 删除
	 * <p>
	 * 根据主键删除
	 * </p>
	 * 
	 * @param <T>
	 *            pojo类
	 * @param clazz
	 *            pojo类-class对象
	 * @param primaryValue
	 *            主键值
	 * @return 数据条数
	 */
	<T> int deleteByPrimaryKey(Class<T> clazz, String primaryValue);

	/**
	 * 更新
	 * <p>
	 * 根据主键更新
	 * </p>
	 * <p>
	 * 更新pojo的所有字段，包括空值(null值)字段
	 * </p>
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
	 * 更新
	 * <p>
	 * 根据主键更新
	 * </p>
	 * <p>
	 * 更新pojo的非空字段
	 * </p>
	 * 
	 * @param <T>
	 *            pojo类
	 * @param t
	 *            pojo对象
	 * @return 数据条数
	 * @throws Exception
	 */
	<T> int updateByPrimaryKeySelective(T t) throws Exception;
}
