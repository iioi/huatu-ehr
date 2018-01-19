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
@Table(name="user_basic_info")
public class UserBasicInfo {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "chinese_name")
	private String chineseName;
//	@Column(name = "pinyin_name")
//	private String pinyinName;//中文拼音,放在dto里面,只在前台显示，不存入数据库
	@Column(name = "english_name")
	private String englishName;
	@Column(name = "sex")
	private Integer sex;//0为男，1为女
	@Column(name = "email")
	private String email;
	
	@Column(name = "country")
	private String country;
	@Column(name = "register_area")
	private String registerArea;//户口所在地
	@Column(name = "register_type")
	private String registerType;//户口性质
	@Column(name = "ethnic")
	private String ethnic;
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "political_status")
	private Integer politicalStatus;//0为中共党员，1为共青团员，2为群众，3为其他党派
	@Column(name = "is_married")
	private Integer isMarried;//0为未婚，1为已婚
	@Column(name = "has_children")
	private Integer hasChildren;//0为未育，1为已育
	@Column(name = "emergency_contact_person")
	private String emergencyContactPerson;
	@Column(name = "emergency_contact_number")
	private String emergencyContactNumber;
	
	@Column(name = "probation_start_time")
	private Date probationStartTime;//入职日期
	@Column(name = "join_date")
	private Date joinDate;//转正日期
	@Column(name = "contrac_end_ime")
	private Date contractEndTime;//合同截止日期
	@Column(name = "id_type")
	private String idType;;
	@Column(name = "id_number")
	private String idNumber;
	
	@Column(name = "address")
	private String address;//现居住地
	@Column(name = "register_address")
	private String registerAddress;//户籍地址
	
	@Column(name = "sys_user_id")
	private String sysUserId;

}
