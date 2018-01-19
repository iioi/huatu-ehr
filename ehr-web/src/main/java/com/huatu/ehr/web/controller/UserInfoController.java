package com.huatu.ehr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huatu.ehr.common.util.ApiResult;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.sal.sys.dto.UserBasicInfoDto;
import com.huatu.ehr.sal.sys.service.UserInfoService;


@RestController("/userinfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/list")
	public ApiResult findBasicInfo() {
		SysUser user = new SysUser();
		UserBasicInfoDto data = userInfoService.findBasicInfo(user);
		return new ApiResult(200, "响应成功", data);	
	}
}
