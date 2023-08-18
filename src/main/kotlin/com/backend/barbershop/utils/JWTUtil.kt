package com.backend.barbershop.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

class JWTUtil {
  private val SECRET_KEY = System.getenv()["SECRET_KEY"]
  private val EXPIRATION_TIME = 86400000  // 1 day in milliseconds

  fun generateToken(username: String): String {
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(Date())
      .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
      .compact()
  }
}