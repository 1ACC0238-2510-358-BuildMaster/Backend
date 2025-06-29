package com.pe.buildmaster_backend.technical_configuration.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.technical_configuration.domain.model.entities.Build
import org.springframework.stereotype.Component

@Component
class BuildRepositoryImpl(
    private val jpaBuildRepository: JpaBuildRepository
) : BuildRepository {

    override fun save(build: Build): Build = jpaBuildRepository.save(build)

    override fun findById(id: Long): Build? = jpaBuildRepository.findById(id).orElse(null)

    override fun findAll(): List<Build> = jpaBuildRepository.findAll()

    override fun deleteById(id: Long) = jpaBuildRepository.deleteById(id)
}