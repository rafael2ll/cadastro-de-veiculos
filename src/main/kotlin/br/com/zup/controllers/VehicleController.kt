package br.com.zup.controllers

import br.com.zup.dto.VehicleDTO
import br.com.zup.services.VehicleService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/vehicles"])
class VehicleController(
    val vehicleService: VehicleService
) {
    @GetMapping(value = ["/{cpf}"])
    fun getUserVehicles(@PathVariable cpf: String): ResponseEntity<List<VehicleDTO>> {
        return ResponseEntity.ok().body(vehicleService.findVehicles(cpf))
    }

    @PostMapping(value = ["/{cpf}"])
    fun addVehicle(@RequestBody vehicle: @Valid VehicleDTO, @PathVariable cpf: String): ResponseEntity<VehicleDTO?> {
        vehicleService.insert(cpf, vehicle)
        return ResponseEntity.status(HttpStatus.valueOf(201)).body(vehicle)
    }
}
