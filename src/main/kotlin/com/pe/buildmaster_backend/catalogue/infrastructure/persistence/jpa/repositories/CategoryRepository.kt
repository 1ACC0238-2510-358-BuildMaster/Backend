package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category

interface CategoryRepository {
    fun findById(id: Long): Category?
    fun findAll(): List<Category>  // en CategoryRepository
}