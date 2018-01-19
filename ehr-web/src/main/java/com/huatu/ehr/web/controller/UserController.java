package com.huatu.ehr.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huatu.ehr.common.util.ApiResult;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.sal.sys.service.UserService;
import com.huatu.ehr.util.CookieUtil;
import com.huatu.ehr.web.util.ImageValidateUtil;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ApiResult login(String username, String password, HttpServletResponse response) {
		ApiResult apiResult = userService.login(username, password, true);
		if (apiResult.getCode() == 200) {
			// 如果登陆成功，将username和token信息写cookie
			SysUser user = (SysUser) apiResult.getData();
			apiResult.setData(null);
			CookieUtil.setCookie(response, "ehr_login_user", user.getUsername(), 10 * 60);
			CookieUtil.setCookie(response, "ehr_login_token", user.getToken(), 10 * 60);
		}
		return apiResult;
	}
	
	@GetMapping("/login/validateImg")
	public void getValidateImg(HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");  
		response.setHeader("Pragma", "no-cache");  
		response.setHeader("Cache-Control", "no-cache");  
		response.setDateHeader("Expires", 0);
		Object[] codeImg = ImageValidateUtil.generateCodeImg();
		
	}
	
}
