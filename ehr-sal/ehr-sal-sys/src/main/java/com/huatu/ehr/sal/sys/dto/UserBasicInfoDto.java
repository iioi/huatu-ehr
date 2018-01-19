package com.huatu.ehr.sal.sys.dto;

import com.huatu.ehr.common.util.PinyinUtils;
import com.huatu.ehr.dal.sys.po.EduExperience;
import com.huatu.ehr.dal.sys.po.UserBasicInfo;
import com.huatu.ehr.dal.sys.po.WorkExperience;

import lombok.Getter;
import lombok.Setter;

public class UserBasicInfoDto {
	@Setter
	@Getter
	private UserBasicInfo userBasicInfo;

	@Setter
	@Getter
	private WorkExperience workExperience;

	@Setter
	@Getter
	private EduExperience eduExperience;

	@Getter
	private String pinyinName;// 中文拼音

	public void setPinyinName() {
		String chineseName = userBasicInfo.getChineseName();
		if (chineseName != null && chineseName.trim().length() != 0) {
			this.pinyinName = PinyinUtils.cn2Spell(userBasicInfo.getChineseName());
		}
	}
}
