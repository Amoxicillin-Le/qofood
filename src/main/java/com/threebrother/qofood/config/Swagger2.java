package com.threebrother.qofood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore注解的API）
                .apis(RequestHandlerSelectors.basePackage("com.threebrother.qofood.controller"))
                // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("qoFood_Server_RESTful APIs")
                .contact("赵小乐子 --- 18729216266@163.com")
                .description("随便看看吧~")
                .termsOfServiceUrl("博客地址~")
                .version("1.0")
                .build();
    }
}
