package com.edi.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edi.domain.TempUser;
import com.edi.dto.EdiResponse;
import com.edi.service.UserService;

/**
 * 系统模块：初始化配置
 * @author jjweng@ibenben.com
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private UserService userService; 
	/**
	 * 重置服务端的配置
	 * @return
	 */
	@RequestMapping(value ="/addUser")
	@ResponseBody
	public EdiResponse<String> addUser(){
		TempUser u=new TempUser();
		u.setAge(15);
		u.setUser("zhangming");
		userService.addUser(u);
		return null;
	}
	/**
	 * 删除temp_user表元素
	 * @delete
	 */
	@RequestMapping(value ="/deleteUser")
	@ResponseBody
	public EdiResponse<String> deleteUser(){
		TempUser u=new TempUser();
		u.setId(2);
		userService.deleteUser(u);
		return null;
	}
	/**
	 * 更新temp_user元素
	 * @update
	 */
	@RequestMapping(value ="/updateUser")
	@ResponseBody
	public EdiResponse<String> updateUser(){
		TempUser u=new TempUser();
		u.setId(3);
		u.setUser("lijun");
		u.setAge(33);
		userService.updateUser(u);
		return null;
	}
	/**
	 * 查询temp_user元素
	 * @select
	 */
	@RequestMapping(value ="/selectUser")
	@ResponseBody
	public EdiResponse<String> selectUser(){
		userService.selectUser();
		return null;
	}
}
