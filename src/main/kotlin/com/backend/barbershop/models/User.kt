package com.backend.barbershop.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users")
class User : UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0

  @Column(name = "username", unique = true, nullable = false)
  @NotNull(message = "Username is required")
  var username: String = ""

  @Column(name = "password", nullable = false)
  @JsonIgnore
  var password: String = ""

  @Column(name = "email", unique = true, nullable = false)
  @NotNull(message = "Email is required")
  var email: String = ""

  @Column(name = "account_non_expired", unique = true, nullable = false)
  var accountNonExpired: Boolean = true

  @Column(name = "account_non_locked", unique = true, nullable = false)
  var accountNonLocked: Boolean = true

  fun encodePassword() {
    val encoder = BCryptPasswordEncoder()
    this.password = encoder.encode(this.password)
  }

  override fun getAuthorities(): MutableCollection<GrantedAuthority> {
    return mutableListOf()
  }

  override fun getPassword(): String {
    return this.password
  }

  override fun getUsername(): String {
    return this.username
  }

  override fun isAccountNonExpired(): Boolean {
    return this.accountNonExpired
  }

  override fun isAccountNonLocked(): Boolean {
    return this.accountNonLocked
  }

  override fun isCredentialsNonExpired(): Boolean {
    return this.isCredentialsNonExpired
  }

  override fun isEnabled(): Boolean {
    return this.isEnabled
  }
}