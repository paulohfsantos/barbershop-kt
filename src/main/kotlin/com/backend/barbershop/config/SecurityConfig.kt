package com.backend.barbershop.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
  @Bean
  @Throws(Exception::class)
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    return http
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/api/**")
      .permitAll()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .and()
      .httpBasic()
      .and()
      .build()
  }

  @Bean
  fun webSecurityCustomizer(): WebSecurityCustomizer {
    return WebSecurityCustomizer { web: WebSecurity ->
      web.ignoring().antMatchers("/api/**")
    }
  }
}
