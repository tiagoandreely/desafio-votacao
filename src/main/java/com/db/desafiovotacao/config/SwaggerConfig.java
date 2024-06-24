package com.db.desafiovotacao.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("DB Desafio Votação")
                                            .description("API responsável pela contagem de votos")
                                            .version("1.0")
                                            .contact(new Contact().name("Tiago Ely")
                                                                  .email("tiagoandreely@gmail.com")))
                            .externalDocs(new ExternalDocumentation().description("Repositório")
                                                                     .url("https://github.com/tiagoandreely/desafio-votacao"));
    }
}
