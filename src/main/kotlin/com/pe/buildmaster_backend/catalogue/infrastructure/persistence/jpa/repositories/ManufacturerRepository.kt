package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer

interface ManufacturerRepository {
    fun findById(id: Long): Manufacturer?
    fun findAll(): List<Manufacturer>

}