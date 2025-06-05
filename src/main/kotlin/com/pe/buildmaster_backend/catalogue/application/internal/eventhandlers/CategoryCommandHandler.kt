package com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateCategoryCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryCommandHandler(
    private val categoryRepository: JpaCategoryRepository
) {

    @Transactional
    fun handleCreate(command: CreateCategoryCommand): Category {
        val parent = command.parentId?.let {
            categoryRepository.findById(it)
                .orElseThrow { IllegalArgumentException("Categoría padre no encontrada con ID $it") }
        }
        val category = Category(name = command.name, parent = parent)
        return categoryRepository.save(category)
    }

    @Transactional
    fun handleDelete(id: Long) {
        val category = categoryRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Categoría no encontrada con ID $id") }
        categoryRepository.deleteById(category.id)
    }
}
