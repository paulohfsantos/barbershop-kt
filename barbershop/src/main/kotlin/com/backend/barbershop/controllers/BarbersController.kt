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
  fun addBarber(@RequestBody barber: Barbers): Barbers {
    try {
      logger.info("try -> $barber")
      return barbersService.addBarber(barber)
    } catch (e: ResponseException) {
      logger.info("error -> $e")
      throw e
    }
  }

  @DeleteMapping("/{id}")
  fun deleteBarber(@PathVariable id: Long) {
    try {
      barbersService.deleteBarber(id)
    } catch (e: ResponseException) {
      throw e
    }
  }

  @PutMapping("/{id}")
  fun updateBarber(
    @PathVariable id: Long,
    @RequestBody barber: Barbers
  ): Barbers {
    try {
      return barbersService.updateBarber(id, barber)
    } catch (e: ResponseException) {
      throw e
    }
  }
}