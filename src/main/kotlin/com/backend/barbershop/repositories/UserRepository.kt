package com.backend.barbershop.repositories

import com.backend.barbershop.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {
  fun findByEmail(email: String): Optional<User>
}