package com.backend.barbershop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig {
  @Bean
  @Throws(Exception::class)
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    return http
      .cors().and()
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/api/public").permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .and()
      .httpBasic()
      .and()
      .build()
  }

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

  @Bean
  fun webSecurityCustomizer(): WebSecurityCustomizer {
    return WebSecurityCustomizer { web: WebSecurity ->
      web.ignoring().antMatchers("/api/public/**")
    }
  }
}
