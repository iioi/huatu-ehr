package com.huatu.ehr.common.errorcode;

public enum ErrorSide {
    UNKNOW(0), SERVER(2), CLIENT(1);
    private int code;
    private ErrorSide(int code){
        this.code = code;
    }
    public int getErrorSideCode(){
        return code;
    }
}
