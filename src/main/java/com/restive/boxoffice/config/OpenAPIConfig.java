package com.restive.boxoffice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Aswathy",
                        email = "aswathyka8@gmail.com"
                ),
                description = "OpenAPI specification for BoxOfficeManager",
                title = "BoxOfficeManager Specification"
        ),
        servers = @Server(
                description = "Local ENV",
                url = "http://localhost:8090"
        )
)
public class OpenAPIConfig {
}
