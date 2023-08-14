package com.backend.barbershop.repositories

import com.backend.barbershop.models.Salon
import org.springframework.data.jpa.repository.JpaRepository

interface SalonRepository: JpaRepository<Salon, Long> {}