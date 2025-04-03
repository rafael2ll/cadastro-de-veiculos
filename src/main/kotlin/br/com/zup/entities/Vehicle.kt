package br.com.zup.entities

import jakarta.persistence.*

@Entity
@Table(name = "tb_vehicle")
class Vehicle (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Column(nullable = false)
    var brand: String,

    @Column(nullable = false)
    var model: String,

    @Column(nullable = false)
    var yearAndFuel: String,

    @ManyToOne
    @JoinColumn(name = "owner_cpf")
    var user: User
)
