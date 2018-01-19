package com.huatu.ehr.web.Interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.util.CookieUtil;

public class SecurityInterceptor implements HandlerInterceptor {

	@Value("${redis.key.prefix}")
	private String redisKeyPrefix;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Cookie> cookieMap = CookieUtil.readCookieMap((HttpServletRequest) request);
		Cookie usernaneCookie = cookieMap.get("ehr_login_user");
		if (usernaneCookie != null) {
			String username = usernaneCookie.getValue();
			Cookie tokenCookie = cookieMap.get("ehr_login_token");
			if (tokenCookie != null) {
				String token = tokenCookie.getValue();
				String userJsonStr = redisTemplate.opsForValue().get(redisKeyPrefix + username);
				SysUser sysUser = JSONObject.parseObject(userJsonStr, SysUser.class);
				if (sysUser.getToken().equals(token)) {
					System.out.println("haha");
				}
			}
		}
		return true;
	}
}
