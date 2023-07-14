package com.backend.barbershop.services

import com.backend.barbershop.models.Reservation
import com.backend.barbershop.repositories.BarberRepository
import com.backend.barbershop.repositories.ReservationRepository
import org.springframework.stereotype.Service
// import sl4j for logging
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@Service
class ReservationService(
  private val logger: Logger = LoggerFactory.getLogger(ReservationService::class.java),
  private val barberRepository: BarberRepository,
  private val reservationRepository: ReservationRepository
) {
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

  fun addReservation(salonId: Long, customer: String, time: LocalDateTime): Reservation {
    logger.info("reservation list -> $salonId, $customer")
    val salon = reservationRepository.findById(salonId).get()
    // val isBarberAvailable = barberRepository.findAll()

    return reservationRepository.save(
      Reservation(
        salon = salon.salon,
        customer = customer,
        time = time,
      )
    )
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