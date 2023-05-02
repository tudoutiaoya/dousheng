package com.zzqedu.dousheng.core.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    OK(0, "success"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(-999999, "系统执行出错");

    // 自定义状态码
    private final int code;

    // 自定义描述符
    private final String message;

}
