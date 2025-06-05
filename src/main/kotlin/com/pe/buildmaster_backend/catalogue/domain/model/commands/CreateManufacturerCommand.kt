package com.pe.buildmaster_backend.catalogue.domain.model.commands

data class CreateManufacturerCommand(
    val name: String,
    val website: String,
    val supportEmail: String
)