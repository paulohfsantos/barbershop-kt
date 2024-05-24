package com.backend.barbershop.services

import com.backend.barbershop.models.Reservation
import com.backend.barbershop.repositories.BarberRepository
import com.backend.barbershop.repositories.ReservationRepository
import com.backend.barbershop.repositories.SalonRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService {
  @Autowired
  private lateinit var barberRepository: BarberRepository

  @Autowired
  private lateinit var reservationRepository: ReservationRepository

  private val logger: Logger = LoggerFactory.getLogger(ReservationService::class.java)

  @Autowired
  private lateinit var salonRepository: SalonRepository

  fun getReservations(salonId: Long): List<Reservation> {
    logger.info("reservation list -> $salonId")
    return reservationRepository.findAll()
      .filter {
        it.salon?.id == salonId
      }
  }

  fun getReservation(id: Long): Reservation {
    logger.info("reservation by id -> $id")
    return reservationRepository.findById(id).get()
  }

  fun addReservation(
    salonId: Long,
    customer: String,
    time: LocalDateTime,
  ): Reservation {
    logger.info("reservation list -> $salonId, $customer")
    val salon = salonRepository.findById(salonId).get()

    return reservationRepository.save(Reservation(
      salon = salon,
      customer = customer,
      time = time,
    ))
  }

  fun deleteReservation(salonId: Long, reservationId: Long) {
    val salon = reservationRepository.findById(salonId).get()
    val reservationId = reservationRepository.findById(reservationId).get()

    reservationRepository.delete(reservationId.copy(salon = salon.salon))
  }

  fun updateReservation(salonId: Long, reservationId: Long, customer: String): Reservation {
    val salon = reservationRepository.findById(salonId).get()
    val reservation = reservationRepository.findById(reservationId).get()

    return reservationRepository.save(reservation.copy(
      salon = salon.salon,
      customer = customer,
    ))
  }
}