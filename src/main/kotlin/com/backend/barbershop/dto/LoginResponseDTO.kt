package com.backend.barbershop.dto

class LoginResponseDTO(
  val token: String,
  val user: UserResponseDTO
)