package com.edi.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edi.domain.TempUser;
import com.edi.mapper.UserMapper;


/**
 * 系统模块：初始化配置
 * @author jjweng@ibenben.com
 *
 */
@Service
public class UserService {
	@Autowired
    private UserMapper userMapper;
	public void addUser(TempUser u) {
		userMapper.insert(u);
		//userMapper.updateByPrimaryKey(u);
		//userMapper.deleteByPrimaryKey(u);
		//userMapper.selectAll();
	}
	
	public void deleteUser(TempUser u) {
		userMapper.deleteByPrimaryKey(u);
	}
	
	public void updateUser(TempUser u) {
		userMapper.updateByPrimaryKey(u);
	}
	
	public void selectUser() {
		userMapper.selectAll();
	}
}
