package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.CategoryComponent
import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.Guide
import org.springframework.stereotype.Repository

@Repository
class GuideRepositoryImpl : GuideRepository {
    override fun findByCategory(categoria: String): Guide {
        return Guide("101", CategoryComponent(categoria), "Contenido de la guía para $categoria")
    }
}