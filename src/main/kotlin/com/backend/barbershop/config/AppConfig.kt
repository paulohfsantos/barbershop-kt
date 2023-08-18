package com.backend.barbershop.config

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@RequiredArgsConstructor
class AppConfig {
  @Bean
  fun comparePasswords(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }
}