package com.pe.buildmaster_backend.catalogue.interfaces.rest.controllers
import com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers.ManufacturerCommandHandler
import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateManufacturerCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaManufacturerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/manufacturers")
class ManufacturerController(
    private val manufacturerRepository: JpaManufacturerRepository,
    private val manufacturerCommandHandler: ManufacturerCommandHandler
) {

    @GetMapping
    fun getManufacturers(): ResponseEntity<List<Map<String, Any>>> {
        val manufacturers = manufacturerRepository.findAll()
        val result = manufacturers.map { manufacturer ->
            mapOf(
                "id" to manufacturer.id,
                "name" to manufacturer.name
            )
        }
        return ResponseEntity.ok(result)
    }

    @PostMapping
    fun createManufacturer(@RequestBody command: CreateManufacturerCommand): ResponseEntity<Manufacturer> {
        val manufacturer = manufacturerCommandHandler.handleCreate(command)
        return ResponseEntity.ok(manufacturer)
    }

    @DeleteMapping("/{id}")
    fun deleteManufacturer(@PathVariable id: Long): ResponseEntity<Void> {
        manufacturerCommandHandler.handleDelete(id)
        return ResponseEntity.noContent().build()
    }
}