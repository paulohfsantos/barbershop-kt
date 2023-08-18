package com.backend.barbershop.config

import com.backend.barbershop.exceptions.ResponseException
import io.jsonwebtoken.Jwts
import org.slf4j.LoggerFactory
import org.springframework.core.env.get
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTConfig: OncePerRequestFilter() {
  private val secretKey = System.getenv()["SECRET_KEY"]

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    val authorizationHeader = request.getHeader("Authorization")

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      val token = authorizationHeader.substring(7)

      try {
        val username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject

        val authentication = UsernamePasswordAuthenticationToken(username, null, emptyList())
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
      } catch (e: ResponseException) {
        throw e
      }
    }

    filterChain.doFilter(request, response)
  }
}