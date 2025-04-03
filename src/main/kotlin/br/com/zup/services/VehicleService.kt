package br.com.zup.services

import br.com.zup.dto.VehicleDTO
import br.com.zup.entities.Vehicle
import br.com.zup.repositories.UserRepository
import br.com.zup.repositories.VehicleRepository
import br.com.zup.services.exceptions.DataErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
class VehicleService(
    val repository: VehicleRepository,
    val userRepository: UserRepository,
    val feignService: FeignService
) {
    @Transactional
    fun insert(cpf: String, dto: VehicleDTO) {
        val user = userRepository.findById(cpf).orElseThrow { DataErrorException("Entity not found! ") }!!
        val vehicle = Vehicle(
            id = 0,
            brand = dto.brand,
            model = dto.model,
            yearAndFuel = dto.yearAndFuel,
            user = user
        )
        repository.save(vehicle)
    }

    @Transactional(readOnly = true)
    fun findVehicles(cpf: String): List<VehicleDTO> {
        val user = userRepository.findById(cpf).orElseThrow { DataErrorException("Entity not found!") }!!
        val vehicles = mutableListOf<VehicleDTO>()
        user.vehicles.forEach {
            vehicles.add(VehicleDTO.fromEntity(it))
        }
        vehicles.forEach(Consumer { vehicle: VehicleDTO ->
            val obj = feignService.getInformations(vehicle)
            val cal = Calendar.getInstance()
            cal.time = Date()
            val day = cal[Calendar.DAY_OF_WEEK]
            vehicle.brand = obj.brand
            vehicle.model = obj.model
            vehicle.price = obj.value
            vehicle.isRotation = feignService.tryRotation(
                day, vehicle.yearAndFuel.substring(3, 4).toInt()
            )
        })
        return vehicles
    }
}
