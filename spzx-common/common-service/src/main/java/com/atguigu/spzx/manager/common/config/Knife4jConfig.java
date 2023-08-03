package com.atguigu.spzx.manager.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * projectName: PACKAGE_NAME
 *
 * @author: ppp
 * time: 2023/7/28 15:35
 * description:
 */
@Configuration
public class Knife4jConfig {
    @Bean
    public GroupedOpenApi adminApi(){
        return GroupedOpenApi.builder()
                .group("admin-api")
                .pathsToMatch("/admin/**")
                .build();
    }
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("尚品甄选API接口文档")
                                .version("1.0")
                                .description("尚品甄选API接口文档")
                                .contact(new Contact().name("atguigu"))

                );
    }
}