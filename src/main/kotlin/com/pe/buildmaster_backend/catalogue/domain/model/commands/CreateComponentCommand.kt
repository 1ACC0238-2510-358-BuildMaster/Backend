package com.pe.buildmaster_backend.catalogue.domain.model.commands

import com.pe.buildmaster_backend.catalogue.domain.model.valueobjects.Specifications

data class CreateComponentCommand(
    val name: String,
    val type: String,
    val price: Double,
    val specifications: Specifications,
    val categoryId: Long,
    val manufacturerId: Long
)