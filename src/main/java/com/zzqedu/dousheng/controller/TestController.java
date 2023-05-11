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

    @GetMapping("/hello2")
    public RestResp hello2() {
        log.info("测试hello2");
        return RestResp.builder().withToken("hello2").build();
    }

    @GetMapping("/hello3")
    public RestResp hello3() {
        log.info("测试hello3");
        return RestResp.builder().withToken("hello3").build();
    }

    @GetMapping("/hello4")
    public RestResp hello4() {
        log.info("测试hello4");
        return RestResp.builder().withToken("hello4").build();
    }

    @GetMapping("/hello5")
    public RestResp hello5() {
        log.info("测试hello4");
        return RestResp.builder().withToken("hello5").build();
    }

    @GetMapping("/hello6")
    public RestResp hello6() {
        log.info("测试hello6");
        return RestResp.builder().withToken("hello6").build();
    }

    @GetMapping("/hello7")
    public RestResp hello7() {
        log.info("测试hello67");
        return RestResp.builder().withToken("hello7").build();
    }

    @GetMapping("/hello8")
    public RestResp hello8() {
        log.info("测试hello8");
        return RestResp.builder().withToken("hello8").build();
    }

}
