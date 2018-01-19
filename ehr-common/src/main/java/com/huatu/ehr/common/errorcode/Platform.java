package com.huatu.ehr.common.errorcode;

public enum Platform {
    UNKNOW(0), IPHONE(1), ANDROID(2), PC(3);
    private int code;
    private Platform(int code){
        this.code = code;
    }
    public int getPlatformCode(){
        return code;
    }
}
