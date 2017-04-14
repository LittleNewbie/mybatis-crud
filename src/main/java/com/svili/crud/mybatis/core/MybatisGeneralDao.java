package com.svili.crud.mybatis.core;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("mybatisGeneralDao")
public interface MybatisGeneralDao {
	
	Map<String, Object> selectByPrimaryKey(Map<String, Object> param);

	int deleteByPrimaryKey(Map<String, Object> param);

	int deleteByCondition(Map<String, Object> param);

	int insert(Map<String, Object> param);

	int insertSelective(Map<String, Object> param);

	int insertBatch(Map<String, Object> param);

	int updateByPrimaryKey(Map<String, Object> param);

	int updateByConditionSelective(Map<String, Object> param);

	List<Map<String, Object>> selectAdvanced(Map<String, Object> param);
}
