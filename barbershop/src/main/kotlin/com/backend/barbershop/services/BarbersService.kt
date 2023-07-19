package com.backend.barbershop.services

import com.backend.barbershop.exceptions.ResponseException
import com.backend.barbershop.models.Salon
import com.backend.barbershop.models.Barbers
import org.springframework.stereotype.Service
import com.backend.barbershop.repositories.BarberRepository
import com.backend.barbershop.repositories.SalonRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus

@Service
class BarbersService(
  val logger: Logger = LoggerFactory.getLogger(BarbersService::class.java),
  private val barberRepository: BarberRepository,
  private val salonRepository: SalonRepository
) {
  fun getBarbers(): List<Barbers> {
    logger.info("barber list")
    return barberRepository.findAll()
  }

  fun getBarber(id: Long): Barbers {
    logger.info("barber -> $id")
    return barberRepository.findById(id).get()
  }

  fun addBarber(barber: Barbers): Barbers {
    val salonData = barber.salon?.let {
      salonRepository.findById(it.id).orElseThrow {
        ResponseException(
          message = "Salon does not exist",
          status = HttpStatus.NOT_FOUND
        )
      }
    }
    barber.salon = salonData

    return barberRepository.save(barber)
  }

  fun deleteBarber(id: Long) {
    logger.info("delete barber -> $id")
    return barberRepository.deleteById(id)
  }

  fun updateBarber(id: Long, barber: Barbers): Barbers {
    logger.info("update barber -> $id, $barber")
    return barberRepository.save(
      Barbers(
        id = id,
        name = barber.name,
        salon = barber.salon,
        reservations = barber.reservations,
      )
    )
  }
}