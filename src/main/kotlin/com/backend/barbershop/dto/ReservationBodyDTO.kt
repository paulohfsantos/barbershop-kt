package com.backend.barbershop.dto

import java.time.LocalDateTime

class ReservationBodyDTO {
  var salonId: Long = 0
  var customer: String = ""
  var time: LocalDateTime = LocalDateTime.now()
}