package com.pe.buildmaster_backend.technical_configuration.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.technical_configuration.domain.model.entities.Build
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaBuildRepository : JpaRepository<Build, Long>