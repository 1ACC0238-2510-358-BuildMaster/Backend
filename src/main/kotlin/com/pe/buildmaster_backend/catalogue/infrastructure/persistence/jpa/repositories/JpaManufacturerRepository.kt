package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ManufacturerRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaManufacturerRepository : JpaRepository<Manufacturer, Long>
