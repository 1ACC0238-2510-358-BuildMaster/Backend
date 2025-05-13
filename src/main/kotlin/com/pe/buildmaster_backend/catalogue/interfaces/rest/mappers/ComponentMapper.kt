package com.pe.buildmaster_backend.catalogue.interfaces.rest.mappers

import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateComponentCommand
import com.pe.buildmaster_backend.catalogue.domain.model.commands.UpdateComponentCommand
import com.pe.buildmaster_backend.catalogue.interfaces.rest.resources.CreateComponentResource
import com.pe.buildmaster_backend.catalogue.interfaces.rest.resources.UpdateComponentResource

object ComponentMapper {
    fun toCreateCommand(resource: CreateComponentResource): CreateComponentCommand {
        return CreateComponentCommand(
            name = resource.name,
            type = resource.type,
            price = resource.price,
            specifications = resource.specifications,
            categoryId = resource.categoryId,
            manufacturerId = resource.manufacturerId
        )
    }

    fun toUpdateCommand(resource: UpdateComponentResource): UpdateComponentCommand {
        return UpdateComponentCommand(
            name = resource.name,
            type = resource.type,
            price = resource.price,
            specifications = resource.specifications,
            categoryId = resource.categoryId,
            manufacturerId = resource.manufacturerId
        )
    }
}
