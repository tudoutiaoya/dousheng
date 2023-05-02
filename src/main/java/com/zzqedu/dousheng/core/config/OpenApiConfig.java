package com.zzqedu.dousheng.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
               title = "dousheng接口文档",
                description = "简介版抖音",
                version = "1.0.0"
        )
)
@Configuration
public class OpenApiConfig {
}
