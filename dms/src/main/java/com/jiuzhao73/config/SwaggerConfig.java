package com.jiuzhao73.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("久曌")
                .apiInfo(getApiInfo()).select().build();
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("久曌", "https://jiuzhao73.com", "2636211752@qq.com");
        return new ApiInfo("久曌的Api文档", "", "v1.0", "https://jiuzhao73.com",
                contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
