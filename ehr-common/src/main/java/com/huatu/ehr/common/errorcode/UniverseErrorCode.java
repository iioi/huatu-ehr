package com.huatu.ehr.common.errorcode;

public interface UniverseErrorCode {

    /**
     * 问题侧：区分问题发生在客户端还是服务器端
     * 
     * @return
     */
    ErrorSide getErrorSide();

    /**
     * 区分问题发生在哪个业务子系统
     * 
     * @return
     */
    Subsystem getSystem();

    /**
     * 平台，区分问题发生在哪个平台。
     * 
     * @return
     */
    Platform getPlatform();

    /**
     * 获取子系统定义的错误码，每个子系统自己定义
     * 
     * @return
     */
    int getSubsystemErrorCode();

    /**
     * 获取错误描述，和子系统错误码一一对应
     * 
     * @return
     */
    String getMessage();

    /**
     * 通过错误码获取对应的错误对象
     * 
     * @param code
     * @return
     */
    UniverseErrorCode fromCode(int code);
}
