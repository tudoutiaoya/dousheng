package com.zzqedu.dousheng.service.impl;


import com.zzqedu.dousheng.entity.User;
import com.zzqedu.dousheng.mapper.UserMapper;
import com.zzqedu.dousheng.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void add(String name, String age) {
        userMapper.add(name, age);
    }

    @Override
    public User get() {
        return userMapper.getOne();
    }

}
