package com.backend.barbershop.repositories

import com.backend.barbershop.models.Barbers
import org.springframework.data.jpa.repository.JpaRepository

interface BarberRepository: JpaRepository<Barbers, Long>