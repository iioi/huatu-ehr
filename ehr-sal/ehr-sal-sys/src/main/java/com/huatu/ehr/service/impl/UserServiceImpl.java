package com.huatu.ehr.service.impl;

import java.util.Date;

import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.huatu.ehr.dao.SysUserDao;
import com.huatu.ehr.dto.ApiResult;
import com.huatu.ehr.entity.SysUser;
import com.huatu.ehr.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${ldap.emailSuffix}")
	private String emailSuffix;

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public ApiResult login(String username, String password, boolean inputIdentifyingCode) {
		// 先查询
		SysUser sysUser = sysUserDao.findByUsername(username);
		if (sysUser == null)
			return new ApiResult(700, "登陆名错误", null);
		// 判断用户是否被禁用
		if (sysUser.getIsForbidden() != null && sysUser.getIsForbidden()) {
			return new ApiResult(700, "该账号以被禁止登陆", null);
		}
		// 判断是否需要输入验证码
		if (sysUser.getIsLocked() != null && sysUser.getIsLocked())
			if (!inputIdentifyingCode)
				return new ApiResult(700, "请输入验证码", null);
		DirContext context = null;
		try {
			context = ldapTemplate.getContextSource().getContext(username + emailSuffix, password);
		} catch (Exception e) {
			// 登陆失败，记录失败时间
			sysUser.setLastTryLogTime(new Date());
			// 记录失败次数，并判断改变lock状态
			sysUser.changeTryNum();
			// 更新数据库
			sysUserDao.update(sysUser, "is_locked", "try_num", "last_try_log_time", "is_forbidden");

		} finally {
			try {
				context.close();
			} catch (Exception e) {
			}
		}
		System.out.println(sysUser);
		return new ApiResult(200, "login success", sysUser);
	}

}
