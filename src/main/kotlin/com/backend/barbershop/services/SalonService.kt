package com.backend.barbershop.services

import com.backend.barbershop.models.Salon
import com.backend.barbershop.repositories.SalonRepository
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class SalonService(
  // private val logger: Logger = LoggerFactory.getLogger(SalonService::class.java),
  private val salonRepository: SalonRepository
) {
  fun getSalons(): List<Salon> {
    return salonRepository.findAll()
  }

  fun getSalon(id: Long): Salon {
    return salonRepository.findById(id).get()
  }

  fun addSalon(salon: Salon): Salon {
    return salonRepository.save(salon)
  }

  fun deleteSalon(id: Long) {
    return salonRepository.deleteById(id)
  }

  fun updateSalon(id: Long, salon: Salon): Salon {
    return salonRepository.save(
      Salon(
        id = id,
        name = salon.name,
        reservations = salon.reservations,
      )
    )
  }
}