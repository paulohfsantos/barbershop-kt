package com.backend.barbershop.controllers

import com.backend.barbershop.dto.LoginDTO
import com.backend.barbershop.dto.LoginResponseDTO
import com.backend.barbershop.dto.RegisterDTO
import com.backend.barbershop.dto.ResponseDTO
import com.backend.barbershop.exceptions.ResponseException
import com.backend.barbershop.models.User
import com.backend.barbershop.services.AuthService
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/public/")
class AuthController {
  @Autowired
  private lateinit var authService: AuthService
  // inject logger
  private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)

  @PostMapping("/login")
  fun login(@RequestBody user: LoginDTO): LoginResponseDTO {
    try {
      logger.info("User ${user.email} is trying to log in")
      return authService.login(user)
    } catch (e: ResponseException) {
      logger.info(e.message)
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