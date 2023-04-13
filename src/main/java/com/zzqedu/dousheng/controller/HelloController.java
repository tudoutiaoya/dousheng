package com.zzqedu.dousheng.controller;

import com.zzqedu.dousheng.entity.User;
import com.zzqedu.dousheng.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.util.MapUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class HelloController {

    @Resource
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }


    @RequestMapping("/add")
    public Map add(@RequestParam String name, @RequestParam String age) {
        log.info("name is {}, age is {}", name, age);
        userService.add(name, age);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("mesaage", "success");
        map.put("data", null);
        return map;
    }

    @RequestMapping("/get")
    public User get() {
        return userService.get();
    }

}
