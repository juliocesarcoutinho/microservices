package br.com.juliocesarcoutinho.userservice.configs;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("Api RestFull Loja Virtual")
                    .version("v1")
                    .description("Api RestFull Loja Virtual")
                    .termsOfService("https://www.github.com/juliocesarcoutinho")
                    .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.github.com/juliocesarcoutinho")
                    )
            );
    }
}