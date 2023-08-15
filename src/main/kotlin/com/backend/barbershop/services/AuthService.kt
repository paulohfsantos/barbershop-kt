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
class AuthService {

  @Autowired
  private lateinit var userRepository: UserRepository
  private val logger: Logger = LoggerFactory.getLogger(AuthService::class.java)

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  fun login(user: LoginDTO): User {
    val userFound = userRepository.findByEmail(user.email).orElseThrow {
      logger.error("Email not found")
      throw UserException("Email not found")
    }
    if (!passwordEncoder.matches(user.password, userFound.password)) {
      logger.error("User $userFound not found")
      throw UserException("Wrong password")
    }

    logger.error("User $userFound logged in")

    return userFound
  }

  fun register(user: RegisterDTO): User {
    if (userRepository.findByEmail(user.email).isPresent) {
      logger.error("Email already in use")
      throw UserException("Email already in use")
    }

    val newUser = User(
      username = user.username,
      email = user.email,
      password = user.password
    )

    if (newUser.password.length < 8) {
      logger.error("Password must be at least 8 characters long")
      throw UserException("Password must be at least 8 characters long")
    }

    newUser.encodePassword()
    return userRepository.save(newUser)
  }
}