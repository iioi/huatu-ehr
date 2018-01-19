package com.huatu.ehr.dal.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.baijia.tianxiao.sqlbuilder.SingleSqlBuilder;
import com.baijia.tianxiao.sqlbuilder.support.JdbcTemplateDaoSupport;
import com.huatu.ehr.dal.sys.dao.WorkExperienceDao;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.dal.sys.po.WorkExperience;

@Repository
public class WorkExperienceDaoImpl extends JdbcTemplateDaoSupport<WorkExperience> implements WorkExperienceDao {

	@Override
	public WorkExperience findWorkExperience(SysUser user) {
		//查询员工教育经历
		SingleSqlBuilder<WorkExperience> workBuilder = SingleSqlBuilder.create(WorkExperience.class);
		workBuilder.eq("userId", user.getId());
		WorkExperience workExperience = uniqueResult(workBuilder);		
		return workExperience;
	}
}
