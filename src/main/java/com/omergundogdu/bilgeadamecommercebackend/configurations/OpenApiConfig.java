package com.omergundogdu.bilgeadamecommercebackend.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI dokümantasyon yapılandırması
 * <p>
 * API hakkında genel bilgileri ekler. Bu bilgiler, Swagger UI'de görüntülenecektir.
 * </p>
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "GND E-ticaret API / Bilgeadam akademi bitirme projesi", version = "v1", description = "GND E-ticaret API dökümantasyonu"))
public class OpenApiConfig {
}
