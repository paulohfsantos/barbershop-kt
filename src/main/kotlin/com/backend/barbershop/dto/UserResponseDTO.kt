package com.backend.barbershop.dto

import com.backend.barbershop.models.User

class UserResponseDTO (
  val id: Long,
  val username: String,
  val email: String,
  val avatar: String
) {
  constructor(user: User, avatar: String) : this(user.id, user.username, user.email, avatar)
}