package com.zzqedu.dousheng.controller;

import com.zzqedu.dousheng.core.common.resp.RestResp;
import com.zzqedu.dousheng.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user", description = "用户服务")
@Validated
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/douyin/user/")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public RestResp register(
            @Length(message = "用户名最长32个字符", max = 32) String username,
            @Length(message = "密码最长32个字符", max = 32) String password) {
        log.info("username{}, password{}", username, password);
        return userService.register(username, password);
    }

}
