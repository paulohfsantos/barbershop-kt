package com.backend.barbershop.exceptions

import org.springframework.http.HttpStatus

class UserException(
  override val message: String
): ResponseException(
  message = message,
  status = when {
    message.contains("User not found") -> HttpStatus.NOT_FOUND
    message.contains("User already exists") -> HttpStatus.CONFLICT
    message.contains("Wrong password") -> HttpStatus.BAD_REQUEST
    message.contains("Email not found") -> HttpStatus.BAD_REQUEST
    message.contains("Invalid password") -> HttpStatus.BAD_REQUEST
    message.contains("Invalid email") -> HttpStatus.BAD_REQUEST
    message.contains("Invalid username") -> HttpStatus.BAD_REQUEST
    else -> HttpStatus.INTERNAL_SERVER_ERROR
  }
)