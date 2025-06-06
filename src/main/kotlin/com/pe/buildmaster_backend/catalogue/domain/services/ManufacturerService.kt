package com.pe.buildmaster_backend.catalogue.domain.services

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateManufacturerCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaManufacturerRepository
import org.springframework.stereotype.Service

@Service
class ManufacturerService(
    private val manufacturerRepository: JpaManufacturerRepository
) {
    fun getAllManufacturers(): List<Manufacturer> {
        return (manufacturerRepository as? JpaManufacturerRepository)?.findAll() ?: emptyList()
    }

    fun create(command: CreateManufacturerCommand): Manufacturer {
        val manufacturer = Manufacturer(
            name = command.name,
            website = command.website,
            supportEmail = command.supportEmail
        )
        return (manufacturerRepository as JpaManufacturerRepository).save(manufacturer)
    }

    fun delete(id: Long) {
        (manufacturerRepository as JpaManufacturerRepository).deleteById(id)
    }
}