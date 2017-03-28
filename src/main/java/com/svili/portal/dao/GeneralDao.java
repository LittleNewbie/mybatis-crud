package com.svili.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.stereotype.Repository;

import com.svili.portal.crud.GeneralDaoProvider;

/**
 * crud通用dao
 * @author svili
 * @date 2016年11月11日
 *
 */
@Repository("generalDao")
public interface GeneralDao {
	
	Map<String,Object> selectByPrimaryKey(Map<String,Object> param);
	
	int deleteByPrimaryKey(Map<String,Object> param);
	
	int deleteByCondition(Map<String,Object> param);
	
	int insert(Map<String,Object> param);
	
	@InsertProvider(type=GeneralDaoProvider.class,method="insertSelectiveSql")
	int insertSelective(Map<String,Object> param);
	
	int insertBatch(Map<String,Object> param);
	
    int updateByPrimaryKey(Map<String,Object> param);
    
    int updateByConditionSelective(Map<String,Object> param);
	
	List<Map<String,Object>> selectAdvanced(Map<String,Object> param);
    
}
