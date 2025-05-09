package com.pe.buildmaster_backend.catalogue.domain.model.queries

data class SearchComponentsQuery(
    val name: String? = null,
    val type: String? = null,
    val categoryId: Long? = null,
    val manufacturerId: Long? = null
)
