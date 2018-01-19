package com.huatu.ehr.dao.impl;

import org.springframework.stereotype.Repository;

import com.baijia.tianxiao.sqlbuilder.SingleSqlBuilder;
import com.baijia.tianxiao.sqlbuilder.support.JdbcTemplateDaoSupport;
import com.huatu.ehr.dao.SysUserDao;
import com.huatu.ehr.entity.SysUser;

@Repository
public class SysUserDaoImpl extends JdbcTemplateDaoSupport<SysUser> implements SysUserDao {

	@Override
	public SysUser findByUsername(String username) {
		SingleSqlBuilder<SysUser> sqlBuilder = createSqlBuilder();
		sqlBuilder.eq("username", username);		
		return uniqueResult(sqlBuilder);
	}

}
