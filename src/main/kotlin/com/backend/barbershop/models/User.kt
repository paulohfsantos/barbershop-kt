package com.backend.barbershop.models

import com.backend.barbershop.dto.UserResponseDTO
import com.backend.barbershop.services.AvatarService
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
data class User (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = 0,

  @NotNull(message = "Username is required")
  @Column(name = "username", unique = true, nullable = false)
  var username: String = "",

  @NotNull(message = "Password is required")
  @Column(name = "password", nullable = false)
  @JsonIgnore
  var password: String = "",

  @NotNull(message = "Email is required")
  @Column(name = "email", unique = true, nullable = false)
  var email: String = "",

  @Column(name = "avatar")
  var avatar: String? = AvatarService.DEFAULT_AVATAR
) {
  fun encodePassword() {
    val encoder = BCryptPasswordEncoder()
    this.password = encoder.encode(this.password)
  }

  fun convertToDTO(): UserResponseDTO {
    return UserResponseDTO(this, this.avatar!!)
  }
}