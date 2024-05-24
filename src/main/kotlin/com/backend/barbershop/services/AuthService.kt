package com.backend.barbershop.services

import com.backend.barbershop.dto.*
import com.backend.barbershop.exceptions.UserException
import com.backend.barbershop.models.User
import com.backend.barbershop.repositories.UserRepository
import com.backend.barbershop.utils.JWTUtil
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthService {

  @Autowired
  private lateinit var userRepository: UserRepository

  private val jwtUtil = JWTUtil()

  @Autowired
  private lateinit var passwordEncoder: PasswordEncoder

  fun login(user: LoginDTO): LoginResponseDTO {
    val userFound = userRepository.findByEmail(user.email).orElseThrow {
      throw UserException("Email not found")
    }
    if (!passwordEncoder.matches(user.password, userFound.password)) {
      throw UserException("Wrong password")
    }

    val token = jwtUtil.generateToken(userFound.username)

    val userResponse = UserResponseDTO(
      id = userFound.id,
      username = userFound.username,
      email = userFound.email,
      avatar = userFound.avatar ?: ""
    )

    return LoginResponseDTO(token, userResponse)
  }

  fun register(user: RegisterDTO): User {
    if (userRepository.findByEmail(user.email).isPresent) {
      throw UserException("Email already in use")
    }

    val newUser = User(
      username = user.username,
      email = user.email,
      password = user.password
    )

    if (newUser.password.length < 8) {
      throw UserException("Password must be at least 8 characters long")
    }

    newUser.encodePassword()
    return userRepository.save(newUser)
  }
}