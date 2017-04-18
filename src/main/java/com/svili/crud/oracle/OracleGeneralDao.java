package com.svili.crud.oracle;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * oracle general dao</br>
 * oracle SQL返回的列名为大写</br>
 * oracle insert/update语句中,Date类型变量需要指定jdbcType</br>
 * oracle batchInsert语句需要特殊处理,大批量数据不建议采用此方式</br>
 * @author svili
 * @data 2017年4月18日
 *
 */
@Repository("oracleGeneralDao")
public interface OracleGeneralDao {

	Map<String, Object> selectByPrimaryKey(Map<String, Object> param);

	int deleteByPrimaryKey(Map<String, Object> param);

	int deleteByCondition(Map<String, Object> param);

	int insert(Map<String, Object> param);

	int insertBatch(Map<String, Object> param);

	int updateByPrimaryKey(Map<String, Object> param);

	int updateByCondition(Map<String, Object> param);

	List<Map<String, Object>> selectAdvanced(Map<String, Object> param);
}
