package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import org.springframework.stereotype.Repository

@Repository
class ComponentRepositoryImpl(
    private val jpaRepository: JpaComponentRepository
) : ComponentRepository {

    override fun save(component: Component): Component = jpaRepository.save(component)

    override fun findById(id: Long): Component? = jpaRepository.findById(id).orElse(null)

    override fun findAll(): List<Component> = jpaRepository.findAll()

    override fun deleteById(id: Long) {
        jpaRepository.deleteById(id)
    }

    override fun search(
        type: String?,
        categoryId: Long?,
        minPrice: Double?,
        maxPrice: Double?,
        manufacturerId: Long?
    ): List<Component> = jpaRepository.findAll() // Provisional
}