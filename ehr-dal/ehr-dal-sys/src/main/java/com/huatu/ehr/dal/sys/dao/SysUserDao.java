package com.huatu.ehr.dal.sys.dao;

import com.baijia.tianxiao.sqlbuilder.support.CommonDao;
import com.huatu.ehr.dal.sys.po.SysUser;

public interface SysUserDao extends CommonDao<SysUser> {

	SysUser findByUsername(String username);
}
