package com.backend.barbershop.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
class UnsupportedMediaTypeException(
  message: String = "Not Found",
  cause: Throwable? = null
): IllegalArgumentException(message, cause) {
  constructor(vararg types: String, cause: Throwable? = null):
          this("Unsupported media type, Supported types: ${types.toList()}", cause)
}