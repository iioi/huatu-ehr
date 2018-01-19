package com.huatu.ehr.dal.sys.dao;

import com.baijia.tianxiao.sqlbuilder.support.CommonDao;
import com.huatu.ehr.dal.sys.po.EduExperience;
import com.huatu.ehr.dal.sys.po.SysUser;

public interface EduExperienceDao extends CommonDao<EduExperience> {
	
	EduExperience findEduExperience(SysUser user);
}
