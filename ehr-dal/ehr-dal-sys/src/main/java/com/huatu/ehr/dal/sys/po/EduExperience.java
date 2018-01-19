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
@Table(name = "edu_experience")
public class EduExperience {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "school")
	private String school	;
	@Column(name = "major")
	private String major	;
	@Column(name = "degree")
	private String degree;
	@Column(name = "is_graduate")
	private Integer isGraduate;//1为统招，0为其他
	
	@Column(name = "sys_user_id")
	private String sysUserId;
}
