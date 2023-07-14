package com.backend.barbershop.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "barbers")
data class Barbers(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  @Column(nullable = false)
  val name: String = "",

  @Column(nullable = false)
  val address: String = "",

  @ElementCollection
  @CollectionTable(name = "barber_services", joinColumns = [JoinColumn(name = "barber_id")])
  @Column(name = "service")
  val services: List<String> = listOf(),

  @Column(nullable = false)
  val is_available: Boolean = false,

  @JsonManagedReference
  @OneToMany(mappedBy = "barber", cascade = [CascadeType.ALL])
  var reservations: List<Reservation> = listOf(),

  // barber to salon
   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "salon_id")
   val salon: Salon? = null,
)