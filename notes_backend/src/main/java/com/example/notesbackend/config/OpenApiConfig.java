package com.example.notesbackend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration with application-level metadata.
 */
@Configuration
public class OpenApiConfig {

    // PUBLIC_INTERFACE
    @Bean
    public OpenAPI notesOpenAPI() {
        /** Builds the OpenAPI specification metadata for the Notes service. */
        return new OpenAPI()
                .info(new Info()
                        .title("Notes Manager API")
                        .description("Ocean Professional — A clean, modern REST API to manage notes.")
                        .version("0.1.0")
                        .contact(new Contact().name("Notes API").email("support@example.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .addTagsItem(new Tag().name("Notes").description("CRUD operations for notes"))
                .externalDocs(new ExternalDocumentation()
                        .description("Swagger UI")
                        .url("/swagger-ui.html"));
    }
}
