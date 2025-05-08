package com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateComponentCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.domain.services.ComponentService
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.CategoryRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ComponentRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ManufacturerRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CreateComponentCommandHandler(
    private val componentRepository: ComponentRepository,
    private val categoryRepository: CategoryRepository,
    private val manufacturerRepository: ManufacturerRepository,
) {
    fun handle(command: CreateComponentCommand): Component {
        val category = categoryRepository.findById(command.categoryId)
            ?: throw IllegalArgumentException("Categoría no encontrada con id ${command.categoryId}")

        val manufacturer = manufacturerRepository.findById(command.manufacturerId)
            ?: throw IllegalArgumentException("Fabricante no encontrado con id ${command.manufacturerId}")

        val component = Component.from(command, category, manufacturer)
        return componentRepository.save(component)
    }

}