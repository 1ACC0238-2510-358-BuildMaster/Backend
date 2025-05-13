package com.pe.buildmaster_backend.catalogue.domain.services
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.CategoryRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaCategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    fun getAllCategories(): List<Category> {
        return (categoryRepository as? JpaCategoryRepository)?.findAll() ?: emptyList()
    }
}