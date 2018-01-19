package com.huatu.ehr.dal.sys.dao;

import com.baijia.tianxiao.sqlbuilder.support.CommonDao;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.dal.sys.po.UserBasicInfo;

public interface UserBasicInfoDao extends CommonDao<UserBasicInfo> {

	UserBasicInfo findBasicInfo(SysUser user);

}
