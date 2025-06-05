package com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateManufacturerCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaManufacturerRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ManufacturerCommandHandler(
    private val manufacturerRepository: JpaManufacturerRepository
) {

    @Transactional
    fun handleCreate(command: CreateManufacturerCommand): Manufacturer {
        val manufacturer = Manufacturer(
            name = command.name,
            website = command.website,
            supportEmail = command.supportEmail
        )
        return manufacturerRepository.save(manufacturer)
    }

    @Transactional
    fun handleDelete(id: Long) {
        val manufacturer = manufacturerRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Fabricante no encontrado con ID $id") }
        manufacturerRepository.deleteById(manufacturer.id)
    }
}
