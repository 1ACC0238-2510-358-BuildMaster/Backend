package com.pe.buildmaster_backend.catalogue.interfaces.rest.resources

import com.pe.buildmaster_backend.catalogue.domain.model.valueobjects.Specifications

data class CreateComponentResource(
    val name: String,
    val type: String,
    val price: Double,
    val specifications: Specifications,
    val categoryId: Long,
    val manufacturerId: Long
)