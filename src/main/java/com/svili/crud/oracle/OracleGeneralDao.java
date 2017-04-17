package com.svili.crud.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("oracleGeneralDao")
public interface OracleGeneralDao {

	Map<String, Object> selectByPrimaryKey(Map<String, Object> param);

	int deleteByPrimaryKey(Map<String, Object> param);

	int deleteByCondition(Map<String, Object> param);

	int insert(Map<String, Object> param);

	int insertBatch(Map<String, Object> param);

	int updateByPrimaryKey(Map<String, Object> param);

	int updateByConditionSelective(Map<String, Object> param);

	List<Map<String, Object>> selectAdvanced(Map<String, Object> param);
}
