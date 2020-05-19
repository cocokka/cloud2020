package com.cloud.oauth2.client.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchemes()))
                .securityContexts(Collections.singletonList(securityContexts()));
    }


    /**
     * 认证方式使用密码模式
     */
    private SecurityScheme securitySchemes() {
//        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("/oauth/token");
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint("http://localhost:8888/oauth/authorize",
                "client", passwordEncoder().encode("secret"));

        /*LoginEndpoint loginEndpoint = new LoginEndpoint("http://localhost:8888/oauth/token");
        GrantType grantType = new ImplicitGrant(loginEndpoint, "access_token");*/
//        GrantType grantType = new AuthorizationCodeGrant(tokenRequestEndpoint,
//                new TokenEndpointBuilder().url("http://localhost:8888/oauth/token").tokenName("access_token").build());

        TokenEndpoint tokenEndpoint = new TokenEndpoint("http://localhost:8888/oauth/token", "access_token");
        GrantType grantType = new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);

        return new OAuthBuilder()
                .name("OAuth2")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("OAuth2", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        AuthorizationScope authorizationScope = new AuthorizationScope("app", "接口测试");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return authorizationScopes;
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("Oauth2 API")
                // 描述
                .description("Oauth2 API" + "接口文档")
                // 作者信息
                .contact(new Contact("lgp", "http://localhost:80", ""))
                // 版本
                .version("版本号:" + "1.0.0")
                .build();
    }

}
