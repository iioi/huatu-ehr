package com.huatu.ehr.sal.sys.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.naming.directory.DirContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.huatu.ehr.common.util.ApiResult;
import com.huatu.ehr.dal.sys.dao.SysUserDao;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.sal.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Value("${ldap.emailSuffix}")
	private String emailSuffix;

	@Value("${redis.key.prefix}")
	private String redisKeyPrefix;

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public ApiResult login(String username, String password, boolean inputIdentifyingCode) {
		// 先查询
		SysUser sysUser = sysUserDao.findByUsername(username);
		// 如果sys_user表中无数据，登陆成功后插入
		if (sysUser == null) {
			boolean loginRes = ldapLogin(username, password);
			if (loginRes) {
				// 登陆成功，想sys_user表中添加记录
				sysUser = new SysUser(username);
				sysUserDao.save(sysUser, "username");
				return new ApiResult(200, "login success", sysUser);
			} else {
				return new ApiResult(700, "用户名或密码不存在", null);
			}
		} else {
			// 判断用户是否被禁用
			if (sysUser.getIsForbidden() != null && sysUser.getIsForbidden()) {
				return new ApiResult(700, "该账号已被禁止登陆", null);
			}
			// 判断是否需要输入验证码
			if (sysUser.getIsLocked() != null && sysUser.getIsLocked())
				if (!inputIdentifyingCode)
					return new ApiResult(700, "请输入验证码", null);
			boolean loginRes = ldapLogin(username, password);
			if (loginRes) {
				// 登陆成功，将失败计数清空
				sysUser.setTryNum(0);
				sysUser.setIsLocked(false);
				sysUserDao.update(sysUser, "isLocked", "tryNum");

			} else {
				// 登陆失败，记录失败时间
				// 记录失败次数，并判断改变lock状态
				sysUser.changeTryNum();
				// 更新数据库
				sysUserDao.update(sysUser, "isLocked", "tryNum", "lastTryLogTime", "isForbidden");
				if (sysUser.getIsLocked() != null && sysUser.getIsLocked()) {
					String msg = "密码错误，失败" + (8 - sysUser.getTryNum()) + "次后账号锁定！";
					return new ApiResult(701, msg, "need identifying code");
				} else {
					String msg = "密码错误，请重试！";
					return new ApiResult(700, msg, null);
				}
			}
		}
		saveUserInfoToRedis(sysUser);
		System.out.println(sysUser);
		return new ApiResult(200, "login success", sysUser);
	}

	private boolean ldapLogin(String username, String password) {
		DirContext context = null;
		try {
			context = ldapTemplate.getContextSource().getContext(username + emailSuffix, password);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				context.close();
			} catch (Exception e) {
			}
		}
	}

	private String saveUserInfoToRedis(SysUser sysUser) {
		// 将用户信息保存的redis
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		String token = UUID.randomUUID().toString();
		sysUser.setToken(token);
		String userJson = JSONObject.toJSON(sysUser).toString();
		ops.set(redisKeyPrefix + sysUser.getUsername(), userJson, 10, TimeUnit.MINUTES);
		return token;
	}

}
