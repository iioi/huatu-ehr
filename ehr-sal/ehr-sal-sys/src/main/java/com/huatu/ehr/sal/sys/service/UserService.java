package com.huatu.ehr.sal.sys.service;

import com.huatu.ehr.common.util.ApiResult;

public interface UserService {

	ApiResult login(String username, String password, boolean inputIdentifyingCode);
}
