package com.svili.portal.crud;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.svili.portal.pojo.User;
import com.svili.portal.type.DataState;

@Controller
public class CrudTest {

	@Resource(name = "crudService")
	private CrudServiceInter service;

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
		user.setEmail("153955424@.com");
		user.setJob("java programer");
		user.setLoginName("LittleNewbie");
		user.setMobilePhone("18166668888");
		user.setOfficePhone("020-70999-8888");
		user.setPassword("123456");
		user.setState(DataState.UNEFFECT);
		user.setUserName("svili");

		int i = service.insertSelective(user);

		result.put("row", i);

		return result;
	}

	@RequestMapping(value = "/test/crud/updateByPrimaryKey.json")
	@ResponseBody
	public Map<String, Object> test_update() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		User user = new User();
		user.setUserId(15);
		user.setDeptId(1);
		user.setEmail("153955424@.com");
		user.setJob("java programer");
		/*
		 * user.setLoginName("LittleNewbie");
		 * user.setMobilePhone("18166668888");
		 * user.setOfficePhone("020-70999-8888"); user.setPassword("123456");
		 */
		user.setState(DataState.UNEFFECT);
		user.setUserName("svili");

		int i = service.updateByPrimaryKey(user);

		result.put("row", i);

		return result;
	}

	@RequestMapping(value = "/test/crud/updateByPrimaryKeySelective.json")
	@ResponseBody
	public Map<String, Object> test_updateSelective() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		User user = new User();
		user.setUserId(15);
		user.setDeptId(1);
		user.setEmail("153955424@.com");
		user.setJob("java programer");
		user.setLoginName("LittleNewbie");
		user.setMobilePhone("18166668888");
		user.setOfficePhone("020-70999-8888");
		user.setPassword("123456");
		user.setState(DataState.UNEFFECT);
		user.setUserName("svili");

		int i = service.updateByPrimaryKeySelective(user);

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

}
