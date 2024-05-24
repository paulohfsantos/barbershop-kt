package com.backend.barbershop.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "salons")
data class Salon(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  @NotNull(message = "Salon name is required")
  @Column(nullable = false)
  val name: String = "",

  @NotNull(message = "Salon location is required")
  @Column(nullable = false)
  val location: String = "",

  @JsonManagedReference
  @OneToMany(mappedBy = "salon", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
  var reservations: List<Reservation> ?= mutableListOf(),

  @JsonManagedReference
  @OneToMany(mappedBy = "salon", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
  var barbers: List<Barbers> ?= mutableListOf(),
)
