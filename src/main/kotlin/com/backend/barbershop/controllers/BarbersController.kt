package com.backend.barbershop.controllers

import com.backend.barbershop.exceptions.ResponseException
import com.backend.barbershop.models.Barbers
import com.backend.barbershop.dto.ResponseDTO
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
  fun addBarber(@RequestBody barber: Barbers): ResponseDTO {
    try {
      logger.info("try -> $barber")
      barbersService.addBarber(barber).let {
        return ResponseDTO(message = "Barber added successfully")
      }
    } catch (e: ResponseException) {
      logger.info("error -> $e")
      throw e
    }
  }

  @DeleteMapping("/{id}")
  fun deleteBarber(@PathVariable id: Long): ResponseDTO {
    try {
      barbersService.deleteBarber(id).let {
        return ResponseDTO(message = "Barber deleted successfully")
      }
    } catch (e: ResponseException) {
      throw e
    }
  }

  @PutMapping("/{id}")
  fun updateBarber(@PathVariable id: Long, @RequestBody barber: Barbers): ResponseDTO {
    try {
      barbersService.updateBarber(id, barber)
      return ResponseDTO(message = "Barber updated successfully")
    } catch (e: ResponseException) {
      throw e
    }
  }
}