package com.zzqedu.dousheng.core.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {


    OK(0, "success"),
    // 系统错误
    SYSTEM_ERROR(-99999, "系统执行出错"),

    // 用户相关
    USER_NAME_EXIST(10000, "用户已经存在"),

    USER_NOT_EXIST(10001, "用户不存在"),

    PASSWORD_ERROR(100002, "密码不正确"),

    // 权限相关
    USER_LOGIN_EXPIRED(20000, "用户登陆已过期"),

    TOKEN_IS_INVALID(20001, "token非法或已经失效");



    // 自定义状态码
    private final int code;

    // 自定义描述符
    private final String message;

}
