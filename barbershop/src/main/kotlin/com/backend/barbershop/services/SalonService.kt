package com.backend.barbershop.services

import com.backend.barbershop.models.Salon
import com.backend.barbershop.repositories.SalonRepository
import org.springframework.stereotype.Service
// use sl4j for logging
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class SalonService(
  private val logger: Logger = LoggerFactory.getLogger(SalonService::class.java),
  private val salonRepository: SalonRepository
) {
  fun getSalons(): List<Salon> {
    logger.info("salon list")
    return salonRepository.findAll()
  }

  fun getSalon(id: Long): Salon {
    logger.info("salon -> $id")
    return salonRepository.findById(id).get()
  }

  fun addSalon(salon: Salon): Salon {
    logger.info("add salon -> $salon")
    return salonRepository.save(salon)
  }

  fun deleteSalon(id: Long) {
    logger.info("delete salon -> $id")
    return salonRepository.deleteById(id)
  }

  fun updateSalon(id: Long, salon: Salon): Salon {
    logger.info("update salon -> $id, $salon")
    return salonRepository.save(
      Salon(
        id = id,
        name = salon.name,
        reservations = salon.reservations,
      )
    )
  }
}