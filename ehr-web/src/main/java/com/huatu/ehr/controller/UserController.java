package com.huatu.ehr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huatu.ehr.dto.ApiResult;
import com.huatu.ehr.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ApiResult login(String username, String password) {
		return userService.login(username, password, false);
	}
}
