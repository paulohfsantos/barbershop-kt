package com.backend.barbershop.services

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

  fun createUser(user: User) {
    user.password = passwordEncoder.encode(user.password)
    userRepository.save(user)
    logger.info("User ${user.username} created")
  }
}