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
	
	List<Map<String,Object>> selectByCondition(Map<String,Object> param);
	
	int deleteByPrimaryKey(Map<String,Object> param);
	
	@InsertProvider(type=GeneralDaoProvider.class,method="insertSelectiveSql")
	int insert(Map<String,Object> param);
	
	@InsertProvider(type=GeneralDaoProvider.class,method="insertSelectiveSql")
	int insertSelective(Map<String,Object> param);
	
	int updateByPrimaryKeySelective(Map<String,Object> param);

    int updateByPrimaryKey(Map<String,Object> param);
	
	Map<String,Object> selectTest(Map<String,Object> param);
    
}
