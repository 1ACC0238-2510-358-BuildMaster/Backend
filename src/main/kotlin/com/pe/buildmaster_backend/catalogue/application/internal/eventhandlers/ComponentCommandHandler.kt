package com.pe.buildmaster_backend.catalogue.application.handlers

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateComponentCommand
import com.pe.buildmaster_backend.catalogue.domain.model.commands.UpdateComponentCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.CategoryRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ComponentRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ManufacturerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ComponentCommandHandler(
    private val componentRepository: ComponentRepository,
    private val categoryRepository: CategoryRepository,
    private val manufacturerRepository: ManufacturerRepository
) {

    @Transactional
    fun handleCreate(command: CreateComponentCommand): Component {
        val category = categoryRepository.findById(command.categoryId)
            ?: throw IllegalArgumentException("Categoría no encontrada con ID ${command.categoryId}")

        val manufacturer = manufacturerRepository.findById(command.manufacturerId)
            ?: throw IllegalArgumentException("Fabricante no encontrado con ID ${command.manufacturerId}")

        val component = Component.from(command, category, manufacturer)
        return componentRepository.save(component)
    }

    @Transactional
    fun handleUpdate(id: Long, command: UpdateComponentCommand): Component {
        val existing = componentRepository.findById(id)
            ?: throw IllegalArgumentException("Componente no encontrado con ID $id")

        val category = categoryRepository.findById(command.categoryId)
            ?: throw IllegalArgumentException("Categoría no encontrada con ID ${command.categoryId}")

        val manufacturer = manufacturerRepository.findById(command.manufacturerId)
            ?: throw IllegalArgumentException("Fabricante no encontrado con ID ${command.manufacturerId}")

        val updated = existing.updateWith(command.toCreateCommand(), category, manufacturer)
        return componentRepository.save(updated)
    }

    @Transactional
    fun handleDelete(id: Long) {
        val existing = componentRepository.findById(id)
            ?: throw IllegalArgumentException("Componente no encontrado con ID $id")
        componentRepository.deleteById(existing.id)
    }
}
