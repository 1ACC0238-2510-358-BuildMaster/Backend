package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaCategoryRepository
import org.springframework.stereotype.Component

@Component
class CategoryRepositoryImpl(
    private val jpaRepository: JpaCategoryRepository
) : CategoryRepository {

    override fun findById(id: Long): Category? {
        return jpaRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Category> {
        return jpaRepository.findAll()
    }
}