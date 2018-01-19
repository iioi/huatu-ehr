package com.huatu.ehr.entity;

import java.util.Date;

import com.baijia.tianxiao.sqlbuilder.annotation.Column;
import com.baijia.tianxiao.sqlbuilder.annotation.Entity;
import com.baijia.tianxiao.sqlbuilder.annotation.GeneratedValue;
import com.baijia.tianxiao.sqlbuilder.annotation.Id;
import com.baijia.tianxiao.sqlbuilder.annotation.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sys_user")
public class SysUser {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "is_locked")
	private Boolean isLocked;
	@Column(name = "try_num")
	private Integer tryNum;
	@Column(name = "last_try_log_time")
	private Date lastTryLogTime;
	@Column(name = "is_forbidden")
	private Boolean isForbidden;
}