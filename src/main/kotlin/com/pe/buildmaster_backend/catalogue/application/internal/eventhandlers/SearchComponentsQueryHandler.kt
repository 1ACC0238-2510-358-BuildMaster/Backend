package com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.domain.model.queries.SearchComponentsQuery
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ComponentRepository
import org.springframework.stereotype.Service

@Service
class SearchComponentsQueryHandler(
    private val repository: ComponentRepository
) {

    fun handle(query: SearchComponentsQuery): List<Component> {
        return repository.search(
            query.type,
            query.categoryId,
            query.minPrice,
            query.maxPrice,
            query.manufacturerId
        )
    }
}