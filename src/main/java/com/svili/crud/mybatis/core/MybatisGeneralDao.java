package com.svili.crud.mybatis.core;

import java.util.List;
import java.util.Map;

/**
 * mybatis通用CRUD数据访问层接口标准</br>
 * mysql oracle 通用CRUD数据访问层接口定义与此类相同</br>
 * 通过mapper.xml区分其dao差异
 * 
 * @author svili
 * @data 2017年4月18日
 *
 */
// @Repository("mybatisGeneralDao")
public interface MybatisGeneralDao {

	Map<String, Object> selectByPrimaryKey(Map<String, Object> param);

	int deleteByPrimaryKey(Map<String, Object> param);

	int deleteByCondition(Map<String, Object> param);

	int insert(Map<String, Object> param);

	int insertBatch(Map<String, Object> param);

	int updateByPrimaryKey(Map<String, Object> param);

	int updateByCondition(Map<String, Object> param);

	List<Map<String, Object>> selectAdvanced(Map<String, Object> param);
}
