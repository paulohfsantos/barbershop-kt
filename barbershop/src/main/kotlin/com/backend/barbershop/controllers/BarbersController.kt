package com.backend.barbershop.controllers

import com.backend.barbershop.exceptions.ResponseException
import com.backend.barbershop.models.Barbers
import com.backend.barbershop.models.Salon
import com.backend.barbershop.services.BarbersService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/barbers")
class BarbersController(
  val logger: Logger = LoggerFactory.getLogger(BarbersController::class.java),
  val barbersService: BarbersService
) {
  @GetMapping
  fun getBarbers(): List<Barbers> {
    try {
      return barbersService.getBarbers()
    } catch (e: ResponseException) {
      logger.info("error -> $e")
      throw e
    }
  }

  @GetMapping("/{id}")
  fun getBarber(@PathVariable id: Long): Barbers {
    try {
      return barbersService.getBarber(id)
    } catch (e: ResponseException) {
      throw e
    }
  }

  @PostMapping
  fun addBarber(@RequestBody barber: Barbers, salon: Salon): Barbers {
    try {
      return barbersService.addBarber(barber, salon)
    } catch (e: ResponseException) {
      logger.info("error -> $e")
      throw e
    }
  }
}