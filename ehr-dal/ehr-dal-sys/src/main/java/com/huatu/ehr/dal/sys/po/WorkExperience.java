package com.huatu.ehr.dal.sys.po;

import java.util.Date;

import com.baijia.tianxiao.sqlbuilder.annotation.Column;
import com.baijia.tianxiao.sqlbuilder.annotation.Entity;
import com.baijia.tianxiao.sqlbuilder.annotation.GeneratedValue;
import com.baijia.tianxiao.sqlbuilder.annotation.Id;
import com.baijia.tianxiao.sqlbuilder.annotation.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "work_experience")
public class WorkExperience {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "company")
	private String company	;
	@Column(name = "major")
	private String major	;
	@Column(name = "position")
	private String position;
	@Column(name = "resign_reason")
	private String resignReason;
	
	@Column(name = "sys_user_id")
	private String sysUserId;
}
