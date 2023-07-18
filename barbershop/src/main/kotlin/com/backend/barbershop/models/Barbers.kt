package com.backend.barbershop.models

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "barbers")
data class Barbers(
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  val id: Long = 0,

  @NotNull(message = "Name is required")
  @Column(nullable = false)
  val name: String = "",

  @NotNull(message = "Address is required")
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

   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "salon_id")
  var salon: Salon? = null,
)