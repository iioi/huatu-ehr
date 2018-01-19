package com.huatu.ehr.common.util;

import com.baijia.tianxiao.sqlbuilder.dto.PageDto;

import lombok.Data;

@Data
public class ApiResult {

    private long code;
    private String msg;
    private Object data;
    private PageDto pageDto;

    public ApiResult(long code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}