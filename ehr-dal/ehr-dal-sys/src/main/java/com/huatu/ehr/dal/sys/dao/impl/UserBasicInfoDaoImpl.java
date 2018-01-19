package com.huatu.ehr.dal.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.baijia.tianxiao.sqlbuilder.SingleSqlBuilder;
import com.baijia.tianxiao.sqlbuilder.support.JdbcTemplateDaoSupport;
import com.huatu.ehr.dal.sys.dao.UserBasicInfoDao;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.dal.sys.po.UserBasicInfo;

@Repository
public class UserBasicInfoDaoImpl extends JdbcTemplateDaoSupport<UserBasicInfo> implements UserBasicInfoDao{

	@Override
	public UserBasicInfo findBasicInfo(SysUser user) {
		//查询员工基本信息
		SingleSqlBuilder<UserBasicInfo> basicBuilder = SingleSqlBuilder.create(UserBasicInfo.class);
		basicBuilder.eq("userId", user.getId());
		UserBasicInfo userBasicInfo = uniqueResult(basicBuilder);
		return userBasicInfo;
	}
	
	
}
