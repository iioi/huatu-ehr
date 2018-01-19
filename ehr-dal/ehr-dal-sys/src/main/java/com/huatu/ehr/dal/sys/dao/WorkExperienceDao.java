package com.huatu.ehr.dal.sys.dao;

import com.baijia.tianxiao.sqlbuilder.support.CommonDao;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.dal.sys.po.WorkExperience;

public interface WorkExperienceDao extends CommonDao<WorkExperience>{

	WorkExperience findWorkExperience(SysUser user);
}
