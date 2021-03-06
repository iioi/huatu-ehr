package com.huatu.ehr.dal.sys.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baijia.tianxiao.sqlbuilder.annotation.Column;
import com.baijia.tianxiao.sqlbuilder.annotation.Entity;
import com.baijia.tianxiao.sqlbuilder.annotation.GeneratedValue;
import com.baijia.tianxiao.sqlbuilder.annotation.Id;
import com.baijia.tianxiao.sqlbuilder.annotation.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "username")
	@JSONField
	private String username;
	@Column(name = "is_locked")
	private Boolean isLocked;
	@Column(name = "try_num")
	private Integer tryNum;
	@Column(name = "last_try_log_time")
	private Date lastTryLogTime;
	@Column(name = "is_forbidden")
	private Boolean isForbidden;
	
	@JSONField
	private String token;

	public SysUser(String username) {
		this.username = username;
	}

	public void changeTryNum() {
		// 如果用户已经被锁定，再失败3次(一共8次)后，账号禁用
		if (isLocked != null && isLocked) {
			tryNum++;
			if (tryNum == 8)
				isForbidden = true;
			return;
		}
		// 先判断时间
		Date currentDate = new Date();
		// 如果时间差大于5min，将失败计数清零
		if (lastTryLogTime == null || currentDate.getTime() - lastTryLogTime.getTime() > 5 * 60 * 1000) {
			tryNum = 1;
		} else {
			tryNum++;
			if (tryNum == 3)
				isLocked = true;
		}
		// 更新时间
		lastTryLogTime = currentDate;
	}

}