package com.backend.barbershop.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "salons")
data class Salon(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  @Column(nullable = false)
  val name: String = "",

  @Column(nullable = false)
  val location: String = "",

  @JsonManagedReference
  @OneToMany(mappedBy = "salon", cascade = [CascadeType.ALL])
  var reservations: List<Reservation> = mutableListOf(),

  @JsonManagedReference
  @OneToMany
  var barbers: List<Barbers> = mutableListOf(),
)
