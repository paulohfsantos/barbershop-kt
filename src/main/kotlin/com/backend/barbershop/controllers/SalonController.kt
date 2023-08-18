package com.backend.barbershop.controllers

import com.backend.barbershop.exceptions.ResponseException
import com.backend.barbershop.models.Salon
import com.backend.barbershop.services.SalonService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/salons")
class SalonController(
  private val logger: Logger = LoggerFactory.getLogger(SalonController::class.java),
  private val salonService: SalonService
) {
  @GetMapping
  fun getSalons(): List<Salon> {
    try {
      return salonService.getSalons()
    } catch (e: ResponseException) {
      throw e
    }
  }

  @GetMapping("/{id}")
  fun getSalon(@PathVariable id: Long): Salon {
    try {
      return salonService.getSalon(id)
    } catch (e: ResponseException) {
      throw e
    }
  }

  @PostMapping
  fun addSalon(@RequestBody salon: Salon): Salon {
    try {
      return salonService.addSalon(salon)
    } catch (e: ResponseException) {
      throw e
    }
  }

  @DeleteMapping
  fun deleteSalon(@RequestParam id: Long) {
    try {
      salonService.deleteSalon(id)
    } catch (e: ResponseException) {
      throw e
    }
  }

  @PutMapping
  fun updateSalon(@RequestBody id: Long, @RequestBody salon: Salon): Salon {
    try {
      return salonService.updateSalon(id, salon)
    } catch (e: ResponseException) {
      throw e
    }
  }
}