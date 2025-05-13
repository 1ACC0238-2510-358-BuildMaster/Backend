package com.pe.buildmaster_backend.catalogue.domain.services

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ManufacturerRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaManufacturerRepository
import org.springframework.stereotype.Service

@Service
class ManufacturerService(
    private val manufacturerRepository: ManufacturerRepository
) {
    fun getAllManufacturers(): List<Manufacturer> {
        return (manufacturerRepository as? JpaManufacturerRepository)?.findAll() ?: emptyList()
    }
}