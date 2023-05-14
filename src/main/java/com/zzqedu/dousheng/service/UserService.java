package com.zzqedu.dousheng.service;

import com.zzqedu.dousheng.core.common.resp.RestResp;
import com.zzqedu.dousheng.dto.req.LoginReqDto;
import org.springframework.stereotype.Service;


public interface UserService {

    /**
     * 用户注册
     */
    RestResp register(LoginReqDto loginReqDto);

    /**
     *  获取用户by id
     * @param id 用户id
     * @return
     */
    RestResp getUserById(Long id);

    /**
     * 用户登陆
     * @return
     */
    RestResp login(LoginReqDto loginReqDto);

    RestResp currentUser(String userId, String token);

}
