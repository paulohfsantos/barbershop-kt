package com.backend.barbershop.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableMethodSecurity
@SecuritySchemes(
  SecurityScheme(
    name = "BarbershopAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
  )
)
class SecurityConfig {
  @Bean
  fun corsConfigSrc(): CorsConfigurationSource {
    val config = CorsConfiguration()
    config.allowedOrigins = listOf("*")
    config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
    config.allowedHeaders = listOf("Authorization", "Content-Type", "xsrf-token")
    config.exposedHeaders = listOf("xsrf-token")
    val src = UrlBasedCorsConfigurationSource()
    src.registerCorsConfiguration("/**", config)
    return src
  }
}
