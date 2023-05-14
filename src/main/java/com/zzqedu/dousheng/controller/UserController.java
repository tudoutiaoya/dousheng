package com.zzqedu.dousheng.controller;

import com.zzqedu.dousheng.core.common.resp.RestResp;
import com.zzqedu.dousheng.dto.req.LoginReqDto;
import com.zzqedu.dousheng.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "用户服务")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/douyin/user/")
public class UserController {

    private final UserService userService;

    @Operation(summary = "用户注册", description = "输入用户名和密码")
    @PostMapping("/register")
    public RestResp register(@Validated LoginReqDto loginReqDto) {
        log.info("/douyin/user/register 用户注册，loginReqDto{}", loginReqDto);
        return userService.register(loginReqDto);
    }

    @Operation(summary = "用户登陆", description = "输入用户名和密码")
    @PostMapping("/login")
    public RestResp login(@Validated LoginReqDto loginReqDto) {
        log.info("/douyin/user/login 用户登陆，loginReqDto{}", loginReqDto);
        return userService.login(loginReqDto);
    }

    @Operation(summary = "当前用户信息", description = "获取当前用户信息")
    @GetMapping
    public RestResp currentUser(@RequestParam("user_id")String userId, @RequestParam("token")String token) {
        log.info("/douyin/user/ 获取当前用户信息, userId{}, token{}", userId, token);
        return userService.currentUser(userId, token);
    }

}
