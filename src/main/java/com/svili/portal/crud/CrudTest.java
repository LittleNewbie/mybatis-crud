package com.svili.portal.crud;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svili.portal.dao.GeneralDao;
import com.svili.portal.pojo.User;
import com.svili.portal.type.DataState;

@Controller
public class CrudTest {

	@Resource(name = "crudService")
	private CrudServiceInter service;
	
	@Resource(name = "generalDao")
	private GeneralDao dao;

	@RequestMapping(value = "/test/crud/selectByPrimaryKey.json")
	@ResponseBody
	public User test_select() throws Exception {
		
		/*Map<String, Object> result = new HashMap<String, Object>();

		result = service.selectByPrimaryKey(User.class, 1);*/
		User user = service.selectByPrimaryKey(User.class, 1);

		return user;
	}

	@RequestMapping(value = "/test/crud/insertSelective.json")
	@ResponseBody
	public Map<String, Object> test_insert() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		User user = new User();
		user.setDeptId(1);
		user.setEmail("123456789@.com");
		user.setJob("java programer");
		user.setLoginName("LittleNewbie");
		user.setMobilePhone("18166668888");
		user.setOfficePhone("020-70999-8888");
		user.setPassword("123456");
		user.setState(DataState.UNEFFECT);
		user.setUserName("svili");
		user.setCreateTime(new Date());

		int i = service.insertSelective(user);

		result.put("row", i);

		return result;
	}
	
	@RequestMapping(value = "/test/crud/insertBatch.json")
	@ResponseBody
	public Map<String, Object> test_insertBatch() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		List<User> list = new ArrayList<User>();
		
		for(int i =1 ; i <10 ;i++){
			User user = new User();
		user.setDeptId(1+i);
		user.setEmail("123456789@.com"+i);
		user.setJob("java programer"+i);
		user.setLoginName("LittleNewbie"+i);
		user.setMobilePhone("18166668888"+i);
		user.setOfficePhone("020-70999-8888"+i);
		user.setPassword("123456"+i);
		user.setState(DataState.UNEFFECT);
		user.setUserName("svili"+i);
		user.setCreateTime(new Date());
		list.add(user);
		}

		int num = service.insertBatch(list);

		result.put("row", num);

		return result;
	}

	@RequestMapping(value = "/test/crud/updateByPrimaryKey.json")
	@ResponseBody
	public Map<String, Object> test_update() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		User user = new User();
		user.setUserId(13);
		user.setDeptId(1);
		user.setEmail("123456789@.com");
		user.setJob("java programer");
		/*
		 * user.setLoginName("LittleNewbie");
		 * user.setMobilePhone("18166668888");
		 * user.setOfficePhone("020-70999-8888"); user.setPassword("123456");
		 */
		user.setState(DataState.UNEFFECT);
		user.setUserName("svili");
		user.setCreateTime(new Date());

		int i = service.updateByPrimaryKey(user);

		result.put("row", i);

		return result;
	}

	@RequestMapping(value = "/test/crud/updateByPrimaryKeySelective.json")
	@ResponseBody
	public Map<String, Object> test_updateSelective() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		User user = new User();
		user.setUserId(12);
		user.setDeptId(1);
		user.setEmail("123456789@.com");
		user.setJob("java programer");
		user.setLoginName("LittleNewbie");
		user.setMobilePhone("18166668888");
		user.setOfficePhone("020-70999-8888");
		user.setPassword("123456");
		user.setState(DataState.UNEFFECT);
		user.setUserName("svili");
		user.setCreateTime(new Date());

		int i = service.updateByPrimaryKeySelective(user);

		result.put("row", i);

		return result;
	}
	
	@RequestMapping(value = "/test/crud/updateByCondition.json")
	@ResponseBody
	public Map<String, Object> test_updateByCondition() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> columnValueMapping = new HashMap<String, Object>();
		columnValueMapping.put("user_name", "update");
		columnValueMapping.put("dept_id", 99);
		columnValueMapping.put("state", DataState.EFFECT.ordinal());
		
		String conditionExp = "dept_id != #{conditionParam.deptId}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("deptId", 1);
		
		int i = service.updateByConditionSelective(User.class, columnValueMapping, conditionExp, conditionParam);

		result.put("row", i);

		return result;
	}
	
	@RequestMapping(value = "/test/crud/deleteByPrimaryKey.json")
	@ResponseBody
	public Map<String, Object> test_delete() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		int i = service.deleteByPrimaryKey(User.class, "15");

		result.put("row", i);

		return result;
	}
	
	@RequestMapping(value = "/test/crud/deleteByCondition.json")
	@ResponseBody
	public Map<String, Object> test_deleteByCondition() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		String condition = "dept_id != #{conditionParam.deptId}";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptId", 1);
		int i = service.deleteByCondition(User.class,condition,param);

		result.put("row", i);

		return result;
	}
	
	@RequestMapping(value = "/test/crud/selectAdvanced.json")
	@ResponseBody
	public List<User> test_selectAdvanced() throws Exception {
		
		String conditionExp = "user_name = #{conditionParam.user_name}";
		
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("user_name", "svili");
		
		String orderExp = "user_id";
		GeneralQueryParam queryParam = new GeneralQueryParam();
		
		queryParam.setConditionExp(conditionExp);
		queryParam.setConditionParam(conditionParam);
		queryParam.setOrderExp(orderExp);
		queryParam.setPageSize(3);
		queryParam.setPageNo(2);
		
		List<User> result = service.selectAdvanced(User.class, queryParam);

		return result;
	}

}
