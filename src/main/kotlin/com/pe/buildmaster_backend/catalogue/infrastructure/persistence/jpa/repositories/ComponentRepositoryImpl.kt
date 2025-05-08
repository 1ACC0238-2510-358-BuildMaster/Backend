package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ComponentRepository
import org.springframework.stereotype.Repository

@Repository
class ComponentRepositoryImpl(
    private val jpaRepository: JpaComponentRepository
) : ComponentRepository {

    override fun save(component: Component): Component {
        return jpaRepository.save(component)
    }

    override fun findById(id: Long): Component? {
        return jpaRepository.findById(id).orElse(null)
    }

    override fun findAll(): List<Component> {
        return jpaRepository.findAll()
    }

    override fun search(
        type: String?,
        categoryId: Long?,
        minPrice: Double?,
        maxPrice: Double?,
        manufacturerId: Long?
    ): List<Component> {
        // Aquí puedes usar Specification o construir a mano
        return jpaRepository.findAll() // simplificado por ahora
    }
}