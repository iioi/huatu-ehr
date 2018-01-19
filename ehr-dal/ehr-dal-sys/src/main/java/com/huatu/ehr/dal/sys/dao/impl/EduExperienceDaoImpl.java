package com.huatu.ehr.dal.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.baijia.tianxiao.sqlbuilder.SingleSqlBuilder;
import com.baijia.tianxiao.sqlbuilder.support.JdbcTemplateDaoSupport;
import com.huatu.ehr.dal.sys.dao.EduExperienceDao;
import com.huatu.ehr.dal.sys.po.EduExperience;
import com.huatu.ehr.dal.sys.po.SysUser;

@Repository
public class EduExperienceDaoImpl extends JdbcTemplateDaoSupport<EduExperience> implements EduExperienceDao {

	@Override
	public EduExperience findEduExperience(SysUser user) {
		//查询员工教育经历
		SingleSqlBuilder<EduExperience> eduBuilder = SingleSqlBuilder.create(EduExperience.class);
		eduBuilder.eq("userId", user.getId());
		EduExperience eduExperience = uniqueResult(eduBuilder);		
		return eduExperience;
	} 

	

}
