package br.com.zup.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "tb_user")
class User (
    @Id
    var cpf: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var birthDate: Date,

    @OneToMany(mappedBy = "user")
    var vehicles: Set<Vehicle> = HashSet()
)
