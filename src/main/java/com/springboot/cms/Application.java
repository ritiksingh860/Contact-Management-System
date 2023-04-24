package com.springboot.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public Docket docket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("contacts-api")
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.springboot.cms.controller"))
				.paths(PathSelectors.any())
				.build();

	}


	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("Contacts-Management-System-API")
				.description("Contact-Management-System-Apis")
				.licenseUrl("singhritik860@gmail.com")
				.version("1.0")
				.build();
	}
}
