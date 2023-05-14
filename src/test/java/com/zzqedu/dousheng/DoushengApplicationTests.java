package com.zzqedu.dousheng;

import cn.hutool.core.bean.BeanUtil;
import com.zzqedu.dousheng.core.util.JWTUtil;
import com.zzqedu.dousheng.dao.entity.User;
import com.zzqedu.dousheng.dto.resp.UserRespDto;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;


class DoushengApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());
        LocalDateTime localDateTime1 = localDateTime.plusYears(1);
        LocalDate localDate1 = LocalDate.parse("20190910", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate parse = LocalDate.parse("2019-09-10", DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(parse);
    }


    @Test
    void testBcrypt() {
        String password = "123456";

        // 加密
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(encodedPassword);

        // 使用正确密码验证密码是否正确
        boolean flag = BCrypt.checkpw(password, encodedPassword);
        System.out.println(flag);

        // 使用错误密码验证密码是否正确
        flag = BCrypt.checkpw("111222", encodedPassword);
        System.out.println(flag);

        System.out.println("-------------------------------------------");

    }

    @Test
    void testRandom() {
        UserRespDto userRespDto = new UserRespDto();
        User user = new User();
        BeanUtil.copyProperties(user, userRespDto);
        System.out.println(userRespDto);
    }

    @Test
    void testJWT() {
        String token = JWTUtil.createToken(1000L);
        System.out.println(token);
        Long aLong = JWTUtil.checkToken("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODkxNTA4MTksInVzZXJJZCI6MTAwMCwiaWIjoxNjgzOTY2ODE5fQ.wPA0vEyvnBp85axl9xRKisE1M5pRgL4Xvi8R8kC_0qY");
        System.out.println(aLong);
    }

}
