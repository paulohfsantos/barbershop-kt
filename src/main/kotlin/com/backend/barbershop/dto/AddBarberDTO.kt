package com.backend.barbershop.dto

class AddBarberDTO {
  var name: String = ""
  var address: String = ""
  var salonId: Long = 0
  var services = listOf<String>()
  var isAvailable: Boolean = false
}