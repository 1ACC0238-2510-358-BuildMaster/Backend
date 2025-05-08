package com.pe.buildmaster_backend.catalogue.domain.model.aggregates

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import com.pe.buildmaster_backend.catalogue.domain.model.valueobjects.Specifications

class ComponentAggregate private constructor(
    val name: String,
    val type: String,
    val price: Double,
    val specifications: Specifications,
    val category: Category,
    val manufacturer: Manufacturer
) {
    companion object {
        fun create(
            name: String,
            type: String,
            price: Double,
            specifications: Specifications,
            category: Category,
            manufacturer: Manufacturer
        ): ComponentAggregate {
            require(price > 0) { "El precio debe ser mayor a cero" }
            require(name.isNotBlank()) { "El nombre no puede estar vacío" }

            return ComponentAggregate(name, type, price, specifications, category, manufacturer)
        }
    }
}