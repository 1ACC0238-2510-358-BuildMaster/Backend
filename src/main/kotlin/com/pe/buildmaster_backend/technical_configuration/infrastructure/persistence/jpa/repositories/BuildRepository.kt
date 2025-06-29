package com.pe.buildmaster_backend.technical_configuration.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.technical_configuration.domain.model.entities.Build

interface BuildRepository {
    fun save(build: Build): Build
    fun findById(id: Long): Build?
    fun findAll(): List<Build>
    fun deleteById(id: Long)
}