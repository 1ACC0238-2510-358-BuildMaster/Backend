package com.pe.buildmaster_backend.catalogue.domain.model.queries

data class SearchComponentsQuery(
    val type: String? = null,
    val categoryId: Long? = null,
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val manufacturerId: Long? = null
)
