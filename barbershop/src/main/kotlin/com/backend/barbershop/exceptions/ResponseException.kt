package com.backend.Auth.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class ResponseException(
    private val status: HttpStatus,
    override val message: String
):RuntimeException(message) {
    fun handleException(): ResponseEntity<Any> {
        print("data: $message, $status")
        return ResponseEntity.status(this.status).body(
            ErrorResponse(
                message = this.message,
                status = this.status.value()
            )
        )
    }
}