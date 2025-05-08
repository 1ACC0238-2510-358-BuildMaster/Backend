package com.pe.buildmaster_backend.catalogue.interfaces.rest.controllers

import com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers.SearchComponentsQueryHandler
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.domain.model.queries.SearchComponentsQuery
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/catalogue")
class ComponentQueryController(
    private val queryHandler: SearchComponentsQueryHandler
) {
    @GetMapping
    fun searchComponents(
        @RequestParam(required = false) type: String?,
        @RequestParam(required = false) categoryId: Long?,
        @RequestParam(required = false) minPrice: Double?,
        @RequestParam(required = false) maxPrice: Double?,
        @RequestParam(required = false) manufacturerId: Long?
    ): List<Component> {
        val query = SearchComponentsQuery(type, categoryId, minPrice, maxPrice, manufacturerId)
        return queryHandler.handle(query)
    }
}