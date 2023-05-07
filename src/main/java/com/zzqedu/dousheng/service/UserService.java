package com.zzqedu.dousheng.service;

import com.zzqedu.dousheng.core.common.resp.RestResp;
import org.springframework.stereotype.Service;


public interface UserService {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     */
    RestResp register(String username, String password);

    /**
     *  获取用户by id
     * @param id 用户id
     * @return
     */
    RestResp getUserById(Long id);

}
