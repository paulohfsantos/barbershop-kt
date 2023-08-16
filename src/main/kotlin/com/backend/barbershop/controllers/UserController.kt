package com.backend.barbershop.controllers

import com.backend.barbershop.models.User
import com.backend.barbershop.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {
  @Autowired
  private lateinit var userService: UserService

  @GetMapping("/profile")
  fun getProfile(@RequestHeader("Authorization") id: Long): User {
    try {
      return userService.getProfile(id)
    } catch (e: Exception) {
      throw e
    }
  }
}