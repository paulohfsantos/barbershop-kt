package com.backend.barbershop.controllers

import com.backend.barbershop.models.Reservation
import com.backend.barbershop.services.ReservationService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/reservations")
class ReservationController(private val reservationService: ReservationService) {
  @GetMapping
  fun getReservations(@RequestParam salonId: Long): List<Reservation> {
    try {
      return reservationService.getReservations(salonId)
    } catch (e: Exception) {
      throw Exception("Error getting reservations")
    }
  }

  @GetMapping("/{id}")
  fun getReservation(@PathVariable id: Long): Reservation {
    try {
      return reservationService.getReservation(id)
    } catch (e: Exception) {
      throw Exception("Error getting reservation")
    }
  }

  @PostMapping
  fun addReservation(@RequestParam salonId: Long, @RequestParam customer: String, time: LocalDateTime): Reservation {
    try {
      return reservationService.addReservation(salonId, customer, time)
    } catch (e: Exception) {
      throw Exception("Error adding reservation")
    }
  }

  @DeleteMapping
  fun deleteReservation(@RequestParam salonId: Long, @RequestParam reservationId: Long) {
    try {
      reservationService.deleteReservation(salonId, reservationId)
    } catch (e: Exception) {
      throw Exception("Error deleting reservation")
    }
  }

  @PutMapping
  fun updateReservation(
    @RequestParam salonId: Long,
    @RequestParam reservationId: Long,
    @RequestParam customerName: String
  ): Reservation {
    try {
      return reservationService
        .updateReservation(salonId, reservationId, customerName)
    } catch (e: Exception) {
      throw Exception("Error updating reservation")
    }
  }
}