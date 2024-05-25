package com.backend.barbershop.services

import com.backend.barbershop.dto.AddBarberDTO
import com.backend.barbershop.exceptions.ResponseException
import com.backend.barbershop.models.Salon
import com.backend.barbershop.models.Barbers
import org.springframework.stereotype.Service
import com.backend.barbershop.repositories.BarberRepository
import com.backend.barbershop.repositories.SalonRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional

@Service
class BarbersService {

  @Autowired
  private lateinit var barberRepository: BarberRepository

  @Autowired
  private lateinit var salonRepository: SalonRepository

  val logger: Logger = LoggerFactory.getLogger(BarbersService::class.java)

  fun getBarbers(): List<Barbers> {
    logger.info("barber list")
    return barberRepository.findAll()
  }

  fun getBarber(id: Long): Barbers {
    logger.info("barber -> $id")
    return barberRepository.findById(id).get()
  }

  @Transactional
  fun addBarber(barber: AddBarberDTO): Barbers {
    val salon = salonRepository.findById(barber.salonId).orElseThrow {
      ResponseException(
        message = "Salon does not exist",
        status = HttpStatus.NOT_FOUND
      )
    }

    val newBarber = Barbers(
      name = barber.name,
      address = barber.address,
      services = barber.services,
      is_available = barber.isAvailable,
      salon = salon
    )

    logger.info("add barber -> $newBarber")
    return barberRepository.save(newBarber)
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