package com.svili.portal.dao;

import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import com.svili.portal.test.crud.GeneralDaoProvider;

@Repository("generalDao")
public interface GeneralDao {
	
	Map<String,Object> selectByPrimaryKey(Map<String,Object> param);
	
	int deleteByPrimaryKey(Map<String,Object> param);
	
	@InsertProvider(type=GeneralDaoProvider.class,method="insertSelectiveSql")
	int insert(Map<String,Object> param);
	
	@InsertProvider(type=GeneralDaoProvider.class,method="insertSelectiveSql")
	int insertSelective(Map<String,Object> param);
	
	@UpdateProvider(type=GeneralDaoProvider.class,method="updateByPrimaryKeySql")
	int updateByPrimaryKeySelective(Map<String,Object> param);

	@UpdateProvider(type=GeneralDaoProvider.class,method="updateByPrimaryKeySql")
    int updateByPrimaryKey(Map<String,Object> param);
    
}
