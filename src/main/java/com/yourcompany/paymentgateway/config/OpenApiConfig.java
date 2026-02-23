package com.yourcompany.paymentgateway.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI paymentGatewayOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payment Gateway Service API")
                        .description("Payment gateway with transaction creation, completion, QR generation, and invoice APIs.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Payment Gateway Team")
                                .email("support@yourcompany.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/Durba-Biswas-DB/payment-gateway-service"));
    }
}
