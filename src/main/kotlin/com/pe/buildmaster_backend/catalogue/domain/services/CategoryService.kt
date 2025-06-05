package com.pe.buildmaster_backend.catalogue.domain.services
import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateCategoryCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaCategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: JpaCategoryRepository
) {
    fun getAllCategories(): List<Category> {
        return (categoryRepository as? JpaCategoryRepository)?.findAll() ?: emptyList()
    }

    fun create(command: CreateCategoryCommand): Category {
        val parent = command.parentId?.let {
            (categoryRepository as JpaCategoryRepository).findById(it).orElse(null)
        }
        val category = Category(name = command.name, parent = parent)
        return (categoryRepository as JpaCategoryRepository).save(category)
    }

    fun delete(id: Long) {
        (categoryRepository as JpaCategoryRepository).deleteById(id)
    }
}