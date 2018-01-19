package com.huatu.ehr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huatu.ehr.dto.ApiResult;
import com.huatu.ehr.dto.LoginResult;
import com.huatu.ehr.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ApiResult login(String username,String password) {
		LoginResult loginResult = userService.login(username, password);
		if(loginResult==LoginResult.SUCCESS) {
			return new ApiResult(200, "login success", null);
		}
		return new ApiResult(700, "wrong password", null);
	}
}
