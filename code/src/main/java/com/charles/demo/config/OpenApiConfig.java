package com.charles.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(
			new Info()
			.title("Projeto de pessoal - JAVA 18 Spring")
			.version("v1")
			.description("descrição de teste")
			.termsOfService("https://github.com/CharlesGodinho")
			.license(
					new License().name("Apache 2.0").url("https://github.com/CharlesGodinho")));
	}
}