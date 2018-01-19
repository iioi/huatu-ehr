package com.huatu.ehr.common.util;

import com.baijia.tianxiao.sqlbuilder.dto.PageDto;
import com.huatu.ehr.common.errorcode.UniverseErrorCode;

/**
 * Created by zhangrui on 16/9/29.
 */
public class ApiResultUtils {

    /**
     * build无返回数据
     * @return
     */
    public static ApiResult buildApiResult() {
        return new ApiResult(ApiStatus.success.value,null,null);
    }

    /**
     * build有返回数据
     * @param result
     * @return
     */
    public static ApiResult buildApiResult(Object result) {
        return new ApiResult(ApiStatus.success.value,null,result);
    }

    /**
     * build正常的带分页返回结果
     * @param result 返回的数据
     * @param pageDto 分页数据
     * @return
     */
    public static ApiResult buildApiResult(Object result, PageDto pageDto) {
        ApiResult r = new ApiResult(ApiStatus.success.value,null,result);
        r.setPageDto(pageDto);
        return r;
    }
    
    /**
     * build有返回数据
     * @param result
     * @return
     */
    public static ApiResult buildApiResult(UniverseErrorCode errorCode, String message, Object result) {
        return new ApiResult(ErrorCodeUtil.getUniverseErrorCode(errorCode),message,result);
    }
    
    /**
     * build返回异常数据
     */
    public static ApiResult error(UniverseErrorCode errorCode, String message) {
    		return new ApiResult(ErrorCodeUtil.getUniverseErrorCode(errorCode), message, null);
    }
    
    public static ApiResult error(UniverseErrorCode errorCode) {
		return new ApiResult(ErrorCodeUtil.getUniverseErrorCode(errorCode), errorCode.getMessage(), null);
    }
}
