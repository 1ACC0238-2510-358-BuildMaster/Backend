package com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface JpaComponentRepository : JpaRepository<Component, Long>{
    @Query("SELECT c FROM Component c WHERE (:type IS NULL OR c.type LIKE %:type%) " +
            "AND (:categoryId IS NULL OR c.category.id = :categoryId) " +
            "AND (:manufacturerId IS NULL OR c.manufacturer.id = :manufacturerId)")
    fun search(
        @Param("name") name: String?,
        @Param("type") type: String?,
        @Param("categoryId") categoryId: Long?,
        @Param("manufacturerId") manufacturerId: Long?
    ): List<Component>
}