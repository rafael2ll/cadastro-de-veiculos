package br.com.zup.dto

import br.com.zup.entities.User
import br.com.zup.services.validation.UserValidData
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.annotation.Nullable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.br.CPF
import java.util.*


@UserValidData
class UserDTO (
    @NotBlank
    @CPF(message = "invalid CPF")
    var cpf: String,
    @NotBlank
    var name: String,
    @Email(message = "invalid Email")
    @NotBlank
    var email: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    var birthDate: Date,
    var vehicles: List<VehicleDTO>? = mutableListOf()
)  {
    companion object {
        fun fromEntity(entity: User, vehicles: List<VehicleDTO>): UserDTO {
            return UserDTO(
                cpf = entity.cpf,
                name = entity.name,
                email = entity.email,
                birthDate = entity.birthDate,
                vehicles = vehicles,
            )
        }
    }
}
