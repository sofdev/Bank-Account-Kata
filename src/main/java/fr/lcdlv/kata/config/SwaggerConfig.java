package fr.lcdlv.kata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private static final String BASE_PACKAGE = "fr.lcdlv.kata";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30).select().apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
				.build().apiInfo(ApiInfo.DEFAULT).useDefaultResponseMessages(false);
	}

}
