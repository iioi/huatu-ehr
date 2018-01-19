package com.huatu.ehr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatu.ehr.dao.SysUserDao;
import com.huatu.ehr.dto.LoginResult;
import com.huatu.ehr.entity.SysUser;
import com.huatu.ehr.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public LoginResult login(String username, String password) {
		SysUser sysUser = sysUserDao.findByUsername(username);
		System.out.println(sysUser);
		return LoginResult.SUCCESS;
	}

}
