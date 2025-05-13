package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import org.springframework.stereotype.Component

@Component
class ManufacturerRepositoryImpl(
    private val jpaRepository: JpaManufacturerRepository
) : ManufacturerRepository {

    override fun findById(id: Long): Manufacturer? {
        val optional = (jpaRepository as org.springframework.data.jpa.repository.JpaRepository<Manufacturer, Long>)
            .findById(id)
        return optional.orElse(null)
    }
    override fun findAll(): List<Manufacturer> {
        return jpaRepository.findAll()
    }
}