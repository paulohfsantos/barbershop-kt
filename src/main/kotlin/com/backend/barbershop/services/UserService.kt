package com.backend.barbershop.services

import com.backend.barbershop.dto.LoginDTO
import com.backend.barbershop.dto.RegisterDTO
import com.backend.barbershop.exceptions.UserException
import com.backend.barbershop.models.User
import com.backend.barbershop.repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {
  @Autowired
  private lateinit var userRepository: UserRepository
  private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  fun getUserByEmail(email: String): User {
    return userRepository.findByEmail(email).orElseThrow {
      logger.error("Email not found")
      throw UserException("Email not found")
    }
  }

  fun getProfile(id: Long): User {
    return userRepository.findById(id).orElseThrow {
      logger.error("User not found")
      throw UserException("User not found")
    }
  }

  fun getUserById(id: Long): User {
    return userRepository.findById(id).orElseThrow {
      logger.error("User not found")
      throw UserException("User not found")
    }
  }
}