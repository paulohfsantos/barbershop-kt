package com.backend.barbershop.services

import com.backend.barbershop.models.Salon
import com.backend.barbershop.repositories.SalonRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.Optional
import javax.transaction.Transactional

@SpringBootTest
@ActiveProfiles("test")
@Transactional // Rollback after each test
class SalonServiceTests {
  @Autowired
  private var salonRepository = mockk<SalonRepository>()

  @Autowired
  private var salonService = SalonService(salonRepository)

  private final val salonList = mutableListOf(
    Salon(id = 1, name = "Salon A", location = "Location A"),
    Salon(id = 2, name = "Salon B", location = "Location B")
  )
  private final val salonData = Salon(name = "Salon A", location = "Location A")

  val salonOptional = Optional.of(salonList[0])

  @Test
  fun `should return all salons`() {
    every { salonRepository.findAll() } returns salonList
    val result = salonService.getSalons()

    assertEquals(salonList, result)
    verify { salonRepository.findAll() }
  }

  @Test
  fun `should return salon with id 1`() {
    every { salonRepository.findById(1) } returns salonOptional
    val result = salonService.getSalon(1)

    assertEquals(salonList[0], result)
    verify { salonRepository.findById(1) }
  }

  @Test
  fun `should create a salon with reservation and barbers null`() {
    val salon = Salon(name = "Salon A", location = "Location A")
    val result = salonRepository.save(salon)

    assertNull(result.id)

    assertEquals(salon.name, result.name)
    assertEquals(salon.location, result.location)

    assertNull(result.reservations)
    assertNull(result.barbers)
  }
}
