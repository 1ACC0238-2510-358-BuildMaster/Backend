package com.pe.buildmaster_backend.catalogue.interfaces.rest.mappers

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateComponentCommand
import com.pe.buildmaster_backend.catalogue.interfaces.rest.resources.CreateComponentResource

object ComponentMapper {
    fun toCommand(resource: CreateComponentResource): CreateComponentCommand =
        CreateComponentCommand(
            name = resource.name,
            type = resource.type,
            price = resource.price,
            specifications = resource.specifications,
            categoryId = resource.categoryId,
            manufacturerId = resource.manufacturerId
        )
}