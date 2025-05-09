package com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateComponentCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.CategoryRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ComponentRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ManufacturerRepository
import org.springframework.stereotype.Service

@Service
class ComponentCommandHandler(
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
    fun delete(id: Long): Boolean {
        return if (componentRepository.findById(id) != null) {
            componentRepository.deleteById(id)
            true
        } else false
    }
    fun update(id: Long, command: CreateComponentCommand): Component {
        val existing = componentRepository.findById(id)
            ?: throw IllegalArgumentException("Componente no encontrado con id $id")

        val category = categoryRepository.findById(command.categoryId)
            ?: throw IllegalArgumentException("Categoría no encontrada")

        val manufacturer = manufacturerRepository.findById(command.manufacturerId)
            ?: throw IllegalArgumentException("Fabricante no encontrado")

        val updated = existing.updateWith(command, category, manufacturer)
        return componentRepository.save(updated)
    }
}