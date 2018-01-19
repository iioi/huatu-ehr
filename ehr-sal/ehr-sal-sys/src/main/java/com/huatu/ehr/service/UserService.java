package com.huatu.ehr.service;

import com.huatu.ehr.dto.ApiResult;

public interface UserService {

	ApiResult login(String username, String password, boolean inputIdentifyingCode);
}
