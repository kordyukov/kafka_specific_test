package com.example.specifications.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Swagger documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.specifications.rest"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo())
                .securitySchemes(Collections.singletonList(oauth()))
                .securityContexts(Collections.singletonList(context()));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("client")
                .clientSecret("secret")
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }


    private ApiInfo buildApiInfo() {
        return new ApiInfo("test",
                "REST API documentation Headof",
                "test",
                null,
                new Contact("NordClan", "https://nordclan.com", "info@nordclan.com"),
                null,
                null,
                new ArrayList<>());
    }

    private SecurityContext context() {
        return SecurityContext.builder().securityReferences(securityReference()).forPaths(PathSelectors.ant("/api/**"))
                .build();
    }

    private List<SecurityReference> securityReference() {
        return Collections.singletonList(new SecurityReference("oauth", scopes()));
    }

    private OAuth oauth() {
        return new OAuth("oauth",
                Arrays.asList(scopes()),
                Collections.singletonList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token")));
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", ""),
                new AuthorizationScope("write", "")};
    }
}

