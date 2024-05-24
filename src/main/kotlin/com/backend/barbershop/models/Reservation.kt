package com.backend.barbershop.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "reservations")
data class Reservation(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  @ManyToOne
  @JoinColumn(name = "salon_id", nullable = false)
  @JsonBackReference("salon-reservations")
  val salon: Salon,

  @Column(nullable = false)
  val customer: String = "",

  @Column(nullable = false)
  val time: LocalDateTime = LocalDateTime.now(),

  @ManyToOne
  @JoinColumn(name = "barber_id")
  @JsonBackReference("barber-reservations")
  val barber: Barbers? = null,
)
