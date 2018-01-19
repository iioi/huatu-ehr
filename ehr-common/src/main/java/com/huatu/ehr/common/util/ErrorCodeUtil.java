package com.huatu.ehr.common.util;

import com.huatu.ehr.common.errorcode.UniverseErrorCode;

public class ErrorCodeUtil {
    public static long getUniverseErrorCode(UniverseErrorCode universeErrorCode) {
        if (universeErrorCode == null) {
            return 0l;
        }
        return universeErrorCode.getErrorSide().getErrorSideCode() * 1000000000L
            + universeErrorCode.getSystem().getSubsystemCode() * 1000000
            + universeErrorCode.getPlatform().getPlatformCode() * 10000 + universeErrorCode.getSubsystemErrorCode();
    }
}
