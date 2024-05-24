package com.backend.barbershop.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class OpenAPIConfig: WebMvcConfigurer {
  override fun addViewControllers(registry: ViewControllerRegistry) {
    registry.addRedirectViewController("/", "/swagger-ui.html")
    registry.addRedirectViewController("/swagger-ui", "/swagger-ui.html")
  }
}