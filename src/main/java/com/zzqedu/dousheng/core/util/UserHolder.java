package com.zzqedu.dousheng.core.util;

import com.zzqedu.dousheng.dto.resp.UserRespDto;

/**
 * 用户信息 持有类
 */
public class UserHolder {

    /**
     * 保存用户信息
     */
    private static final ThreadLocal<UserRespDto> LOCAL = new ThreadLocal<>();

    public static void put(UserRespDto userRespDto) {
        LOCAL.set(userRespDto);
    }

    public static UserRespDto get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }

}
