package com.zzqedu.dousheng;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.zzqedu.dousheng.dao.mapper")
@SpringBootApplication
public class DoushengApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoushengApplication.class, args);
        log.info("swagger文档接口地址为: http://localhost:9999/swagger-ui/index.html");
    }

}
