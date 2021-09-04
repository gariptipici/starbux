package com.bestseller.starbux.common.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.bestseller.starbux"))
        .paths(PathSelectors.ant("/**"))
        .build()
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(
        "Bestseller Starbux Application",
        "This application demonstrates documenting of Starbux REST API with Swagger using SpringFox.",
        "1.0.0",
        "",
        new Contact("Garip Tipici", "https://www.linkedin.com/in/gariptipici", "gariptipici@gmail.com"),
        "",
        "",
        Collections.emptyList()
    );
  }
}