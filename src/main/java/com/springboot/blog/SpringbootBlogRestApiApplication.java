package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info= @Info(
				title = " spring boot blog app REST APIs",
				description = " spring boot blog app REST APIs description",
				version = "v1.0",
				contact = @Contact(
						name="Nandhini",
						email="nandhinirajavell@gmail.com",
						url="https://someurl.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://licenseinfourl.com"
				)
		),

		externalDocs = @ExternalDocumentation(
						description = "Spring Boot Blog App Documentation",
						url="https://someurlmaybegithub.com"
		)


)
public class SpringbootBlogRestApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

}
