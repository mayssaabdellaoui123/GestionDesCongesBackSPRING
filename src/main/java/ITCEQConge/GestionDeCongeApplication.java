package ITCEQConge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@SpringBootApplication
public class GestionDeCongeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionDeCongeApplication.class, args);
    }

    @Bean
    public Docket SwaggerConfiguration(){
        return new Docket (DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ITCEQConge")).build().apiInfo(apiInfo());}

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Gestion de Congés",
                "Description of APIs.",
                "Spring boot application",
                "Terms of service",
                new Contact("Mayssa + Alaa ","", ""),
                "License of API", "API license URL", Collections.emptyList());
    }
}
