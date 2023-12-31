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

  // TODO: create or refactor update barber to be deactivated instead of deleted

  fun updateBarber(id: Long, barber: Barbers): Barbers {
    logger.info("update barber -> $id, $barber")
    val barberData = barberRepository.findById(id).orElseThrow {
      ResponseException(
        message = "Barber does not exist",
        status = HttpStatus.NOT_FOUND
      )
    }

    barberData.name = barber.name
    barberData.address = barber.address
    barberData.services = barber.services
    barberData.is_available = barber.is_available
    barberData.reservations = barber.reservations
    barberData.salon = barber.salon

    return barberRepository.save(barberData)
  }
}