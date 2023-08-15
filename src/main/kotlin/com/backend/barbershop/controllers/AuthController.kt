package com.backend.barbershop.controllers

import com.backend.barbershop.dto.LoginDTO
import com.backend.barbershop.dto.RegisterDTO
import com.backend.barbershop.exceptions.ResponseException
import com.backend.barbershop.models.User
import com.backend.barbershop.services.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController {
  @Autowired
  private lateinit var authService: AuthService

  @PostMapping("/login")
  fun login(@RequestBody user: LoginDTO): User {
    try {
      return authService.login(user)
    } catch (e: ResponseException) {
      throw e
    }
  }

  @PostMapping("/register")
  fun register(@RequestBody user: RegisterDTO): User {
    try {
      return authService.register(user)
    } catch (e: ResponseException) {
      throw e
    }
  }
}