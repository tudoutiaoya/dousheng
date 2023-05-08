package com.zzqedu.dousheng.controller;


import com.zzqedu.dousheng.core.common.resp.RestResp;
import com.zzqedu.dousheng.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "test", description = "测试服务")
@RequiredArgsConstructor
@RestController
@RequestMapping("/test/")
public class TestController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public RestResp getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }


    @GetMapping("/hello")
    public RestResp hello() {
        log.info("测试hello");
        return RestResp.builder().withToken("hello").build();
    }

}
