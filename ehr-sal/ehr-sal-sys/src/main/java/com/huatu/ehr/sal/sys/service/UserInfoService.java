package com.huatu.ehr.sal.sys.service;

import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.sal.sys.dto.UserBasicInfoDto;

public interface UserInfoService {

	UserBasicInfoDto findBasicInfo(SysUser user);

}
