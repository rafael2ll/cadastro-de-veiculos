package br.com.zup.services

import br.com.zup.dto.UserDTO
import br.com.zup.dto.VehicleDTO
import br.com.zup.entities.User
import br.com.zup.repositories.UserRepository
import br.com.zup.services.exceptions.DataErrorException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.function.Consumer

@Service
class UserService(
    val repository: UserRepository,
    val feignService: FeignService
) {
    @Transactional(readOnly = true)
    fun getUser(cpf: String): UserDTO {
        val user = repository.findById(cpf).orElseThrow { DataErrorException("Entity not found!") }!!
        val vehicles = mutableListOf<VehicleDTO>()
        user.vehicles.forEach {
            vehicles.add(VehicleDTO.fromEntity(it))
        }
        vehicles.forEach(Consumer { vehicle: VehicleDTO ->
            vehicle.rotKey = vehicle.yearAndFuel.substring(3, 4).toInt()
            val cal = Calendar.getInstance()
            cal.time = Date()
            val day = cal[Calendar.DAY_OF_WEEK]
            val obj = feignService.getInformations(vehicle)
            vehicle.price = obj.value
            vehicle.isRotation = feignService.tryRotation(day, vehicle.rotKey!!)
        })
        return UserDTO.fromEntity(user, vehicles)
    }

    @Transactional
    fun insert(dto: UserDTO) {
        val user = User(dto.cpf, dto.name, dto.email, dto.birthDate)
        repository.save(user)
    }
}
