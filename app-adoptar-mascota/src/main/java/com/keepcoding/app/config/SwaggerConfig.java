package com.keepcoding.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	Docket createApiDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.keepcoding.app.controller"))
			.paths(PathSelectors.any())
			.build();		
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Documentación de la Fundación para facilitar la adopción de mascotas")
			.description("Información completa sobre la API REST para consumo de clientes")
			.termsOfServiceUrl("www.linkedin.com/in/fátima-garcía-jorge-b1a999ba")
			.contact(new Contact("Fátima García Jorge", "www.linkedin.com/in/fátima-garcía-jorge-b1a999ba", "fatima.jorge.g@gmail.com"))
			.version("1.0")
			.build();
	}
}
