package com.backend.barbershop.repositories

import com.backend.barbershop.models.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository: JpaRepository<Reservation, Long> {
}