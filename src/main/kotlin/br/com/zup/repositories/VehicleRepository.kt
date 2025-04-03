package br.com.zup.repositories

import br.com.zup.entities.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : JpaRepository<Vehicle?, Int?>
