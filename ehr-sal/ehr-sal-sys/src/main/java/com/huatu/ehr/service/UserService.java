package com.huatu.ehr.service;

import com.huatu.ehr.dto.LoginResult;

public interface UserService {

	LoginResult login(String username,String password);
}
