package com.huatu.ehr.common.util;

public enum ApiStatus {
    success(0);

    long value;

     ApiStatus(int value){
        this.value = value;
    }
}
