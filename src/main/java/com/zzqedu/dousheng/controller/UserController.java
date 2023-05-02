package com.zzqedu.dousheng.controller;

import com.zzqedu.dousheng.core.common.resp.RestResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "用户服务")
@Validated
@Slf4j
@RestController("/douyin/user/")
public class UserController {


    @PostMapping("/register")
    public RestResp register(
            ) {
        //log.info("username{}, password{}", username, password);
        return RestResp.ok();
    }

    @GetMapping("/hello")
    public RestResp hello() {
        return RestResp.ok();
    }


}
