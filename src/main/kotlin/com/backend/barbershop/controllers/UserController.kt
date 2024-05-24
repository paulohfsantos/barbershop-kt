package com.backend.barbershop.controllers

import com.backend.barbershop.dto.UserDTO
import com.backend.barbershop.models.User
import com.backend.barbershop.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/users")
class UserController {
  @Autowired
  private lateinit var userService: UserService

  @GetMapping("/profile")
  fun getProfile(id: Long): User {
    try {
      return userService.getProfile(id)
    } catch (e: Exception) {
      throw e
    }
  }

  @PutMapping("/update-profile")
  fun updateProfile(id: Long, @RequestBody user: UserDTO): User {
    try {
      return userService.updateProfile(id, user)
    } catch (e: Exception) {
      throw e
    }
  }

  @PutMapping("/{id}/save-avatar", consumes = ["multipart/form-data"])
  fun saveAvatar(@PathVariable id: Long, @RequestParam avatar: MultipartFile) {
    try {
      return userService.saveAvatar(id, avatar)
    } catch (e: Exception) {
      throw e
    }
  }
}