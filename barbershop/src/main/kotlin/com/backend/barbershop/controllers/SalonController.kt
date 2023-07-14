package com.backend.barbershop.controllers

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
    } catch (e: Exception) {
      logger.info("error -> $e")
      throw Exception("Error getting salons")
    }
  }

  @GetMapping("/{id}")
  fun getSalon(@PathVariable id: Long): Salon {
    try {
      return salonService.getSalon(id)
    } catch (e: Exception) {
      throw Exception("Error getting salon")
    }
  }

  @PostMapping
  fun addSalon(@RequestParam salon: Salon): Salon {
    try {
      return salonService.addSalon(salon)
    } catch (e: Exception) {
      throw Exception("Error adding salon")
    }
  }

  @DeleteMapping
  fun deleteSalon(@RequestParam id: Long) {
    try {
      salonService.deleteSalon(id)
    } catch (e: Exception) {
      throw Exception("Error deleting salon")
    }
  }

  @PutMapping
  fun updateSalon(@RequestParam id: Long, @RequestParam salon: Salon): Salon {
    try {
      return salonService.updateSalon(id, salon)
    } catch (e: Exception) {
      throw Exception("Error updating salon")
    }
  }
}