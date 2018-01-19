package com.huatu.ehr.dao;

import com.baijia.tianxiao.sqlbuilder.support.CommonDao;
import com.huatu.ehr.entity.SysUser;

public interface SysUserDao extends CommonDao<SysUser> {

	SysUser findByUsername(String username);
}
