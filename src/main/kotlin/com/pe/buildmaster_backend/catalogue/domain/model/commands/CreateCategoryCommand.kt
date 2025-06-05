package com.pe.buildmaster_backend.catalogue.domain.model.commands

data class CreateCategoryCommand(
    val name: String,
    val parentId: Long? = null
)