package com.huatu.ehr.common.errorcode;

public enum Subsystem {
    UNKNOW(0), COMMON(1), PAY_SDK(3), HERMES(4), FORUM(5),

    STUDENT(11),

    TEACHER(12),

    ORGANIZATION(13),

    /**
     * 报名模块
     */
    SIGNUP(14),

    /**
     * CRM-咨询本模块
     */
    CONSULT(15),

    /**
     * CRM-微信模块
     */
    WECHAT(16),

    /**
     * 仪表盘
     */
    DASHBOARD(17),

    /**
     * CRM-今日待办模块
     */
    TODO(18),

    /**
     * 天校100-官网
     */
    WWW(19),

    MSG_CENTER(20),
    
    /**
     * Finance-财务模块
     */
    FINANCE(21),
    
    /**
     * 飞渡
     */
    VIRGO(22),
	
	/**
     * 商学院
     */
    BUSINESSSCHOOL(23),
	
	/**
     * 闻道
     */
    WEDO(24);
    ;

    private int code;

    private Subsystem(int code) {
        this.code = code;
    }

    public int getSubsystemCode() {
        return code;
    }
}
