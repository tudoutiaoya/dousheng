package com.zzqedu.dousheng;

import com.zzqedu.dousheng.dao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestConnection {


    @Resource
    private UserMapper userMapper;

    @Test
    void test() {
        userMapper.selectAll();
    }

}
