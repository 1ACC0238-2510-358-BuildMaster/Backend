package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories


import org.springframework.stereotype.Repository;
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component

@Repository
interface ComponentRepository {
    fun save(component: Component): Component
    fun findById(id: Long): Component?
    fun findAll(): List<Component>
    fun deleteById(id: Long)
    fun search(
        name: String?,
        type: String?,
        categoryId: Long?,
        manufacturerId: Long?
    ): List<Component>
}
