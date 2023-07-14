package com.backend.Auth.exceptions

import java.util.Date

class ExceptionResponse(
    val timestamp: Date,
    val message: String = "",
    val details: String = ""
)