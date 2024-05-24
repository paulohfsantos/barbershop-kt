package com.backend.barbershop.services

import com.backend.barbershop.dto.LoginDTO
import com.backend.barbershop.dto.RegisterDTO
import com.backend.barbershop.dto.UserDTO
import com.backend.barbershop.dto.UserResponseDTO
import com.backend.barbershop.exceptions.UserException
import com.backend.barbershop.models.User
import com.backend.barbershop.repositories.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserService {
  @Autowired
  private lateinit var userRepository: UserRepository

  @Autowired
  private lateinit var avatarService: AvatarService

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

  fun updateProfile(id: Long, user: UserDTO): User {
    val currentUser = getUserById(id)
    currentUser.username = user.username
    currentUser.email = user.email
    currentUser.avatar = user.avatar
    // include password update and validation for password length (later)
    userRepository.save(currentUser)
    return currentUser
  }

  fun saveAvatar(id: Long, avatar: MultipartFile) {
    val user = getUserById(id)
    user.avatar = avatarService.save(user, avatar)
    userRepository.save(user)
  }

  fun toResponse(user: User): String {
    return UserResponseDTO(
      id = user.id,
      username = user.username,
      email = user.email,
      avatar = avatarService.urlFor(user.avatar!!)
    ).toString()
  }
}