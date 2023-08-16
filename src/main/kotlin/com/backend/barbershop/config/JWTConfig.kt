package com.backend.barbershop.config

import io.jsonwebtoken.Jwts
import org.slf4j.LoggerFactory
import org.springframework.core.env.get
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
      val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body
      val username = claims.subject

      if (username != null && SecurityContextHolder.getContext().authentication == null) {
        val authentication = UsernamePasswordAuthenticationToken(username, null, emptyList())
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
      }
    }

    filterChain.doFilter(request, response)
  }
}