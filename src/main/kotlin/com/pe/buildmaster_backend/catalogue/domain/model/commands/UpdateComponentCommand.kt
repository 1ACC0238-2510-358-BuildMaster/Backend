package com.pe.buildmaster_backend.catalogue.domain.model.commands

import com.pe.buildmaster_backend.catalogue.domain.model.valueobjects.Specifications

data class UpdateComponentCommand(
    val name: String,
    val type: String,
    val price: Double,
    val specifications: Specifications,
    val categoryId: Long,
    val manufacturerId: Long
) {
    fun toCreateCommand(): CreateComponentCommand {
        return CreateComponentCommand(
            name = name,
            type = type,
            price = price,
            specifications = specifications,
            categoryId = categoryId,
            manufacturerId = manufacturerId
        )
    }
}
