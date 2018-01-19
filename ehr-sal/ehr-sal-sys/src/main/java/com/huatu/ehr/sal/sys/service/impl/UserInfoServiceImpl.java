package com.huatu.ehr.sal.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huatu.ehr.dal.sys.dao.EduExperienceDao;
import com.huatu.ehr.dal.sys.dao.UserBasicInfoDao;
import com.huatu.ehr.dal.sys.dao.WorkExperienceDao;
import com.huatu.ehr.dal.sys.po.EduExperience;
import com.huatu.ehr.dal.sys.po.SysUser;
import com.huatu.ehr.dal.sys.po.UserBasicInfo;
import com.huatu.ehr.dal.sys.po.WorkExperience;
import com.huatu.ehr.sal.sys.dto.UserBasicInfoDto;
import com.huatu.ehr.sal.sys.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserBasicInfoDao userBasicInfoDao;
	
	@Autowired
	private EduExperienceDao eduExperienceDao;
	
	@Autowired
	private WorkExperienceDao workExperienceDao;
	
	@Override
	public UserBasicInfoDto findBasicInfo(SysUser user) {
		UserBasicInfoDto data = new UserBasicInfoDto();
		UserBasicInfo userBasicInfo = userBasicInfoDao.findBasicInfo(user);
		data.setUserBasicInfo(userBasicInfo);
		EduExperience eduExperience = eduExperienceDao.findEduExperience(user);
		data.setEduExperience(eduExperience);
		WorkExperience workExperience = workExperienceDao.findWorkExperience(user);
		data.setWorkExperience(workExperience);
		return data;
	}
}
