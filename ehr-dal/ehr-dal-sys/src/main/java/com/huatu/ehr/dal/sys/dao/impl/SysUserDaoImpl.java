package com.huatu.ehr.dal.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.baijia.tianxiao.sqlbuilder.SingleSqlBuilder;
import com.baijia.tianxiao.sqlbuilder.support.JdbcTemplateDaoSupport;
import com.huatu.ehr.dal.sys.dao.SysUserDao;
import com.huatu.ehr.dal.sys.po.SysUser;

@Repository
public class SysUserDaoImpl extends JdbcTemplateDaoSupport<SysUser> implements SysUserDao {

	@Override
	public SysUser findByUsername(String username) {
		SingleSqlBuilder<SysUser> sqlBuilder = createSqlBuilder();
		sqlBuilder.eq("username", username);		
		return uniqueResult(sqlBuilder);
	}

}
