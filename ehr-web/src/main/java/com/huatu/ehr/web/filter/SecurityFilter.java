package com.huatu.ehr.web.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSONObject;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.util.CookieUtil;

//@WebFilter(filterName="securityFilter",urlPatterns="/*")
public class SecurityFilter implements Filter {
	
	@Value("${redis.key.prefix}")
	private String redisKeyPrefix;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, Cookie> cookieMap = CookieUtil.readCookieMap((HttpServletRequest) request);
		Cookie usernaneCookie = cookieMap.get("ehr_login_user");
		if(usernaneCookie!=null) {
			String username = usernaneCookie.getValue();
			Cookie tokenCookie = cookieMap.get("ehr_login_token");
			if(tokenCookie!=null) {
				String token = tokenCookie.getValue();
				String userJsonStr = redisTemplate.opsForValue().get(redisKeyPrefix+username);
				SysUser sysUser = JSONObject.parseObject(userJsonStr, SysUser.class);
				if(sysUser.getToken().equals(token)) {
					System.out.println("haha");
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
