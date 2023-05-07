package com.zzqedu.dousheng.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zzqedu.dousheng.core.common.constant.ErrorCodeEnum;
import com.zzqedu.dousheng.core.common.exception.BusinessException;
import com.zzqedu.dousheng.core.common.resp.RestResp;
import com.zzqedu.dousheng.dao.entity.User;
import com.zzqedu.dousheng.dao.mapper.UserMapper;
import com.zzqedu.dousheng.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public RestResp register(String username, String password) {
        // 查看用户是否存在
        if (userMapper.selectByName(username) != null) {
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }
        // 密码加密(Bcrypt)
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User();
        user.setName(username);
        user.setPassword(encodedPassword);
        user.setAvatar("");

        // 保存
        userMapper.insertSelective(user);

        // jwt生成token,但是jwt服务端不可控
        return RestResp.builder()
                .withUserId(user.getId())
                .withToken("")
                .build();
    }

    @Override
    public RestResp getUserById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        String useStr = JSON.toJSONString(user);
        return RestResp.builder().withToken(useStr).build();
    }

}