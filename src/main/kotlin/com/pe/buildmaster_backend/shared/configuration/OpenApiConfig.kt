package org.example.com.pe.buildmaster_backend.shared.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun api(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("BuildMaster API")
                .description("Documentación de la API para gestión de componentes")
                .version("1.0")
        )
    }
}