package com.pe.buildmaster_backend.catalogue.interfaces.rest.controllers

import com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers.SearchComponentsQueryHandler
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.domain.model.queries.SearchComponentsQuery
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ComponentRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/catalogue")
class ComponentQueryController(
    private val queryHandler: SearchComponentsQueryHandler,
    private val componentRepository: ComponentRepository
) {
    @GetMapping
    fun searchComponents(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) type: String?,
        @RequestParam(required = false) categoryId: Long?,
        @RequestParam(required = false) manufacturerId: Long?
    ): List<Component> {
        val query = SearchComponentsQuery(name,type, categoryId, manufacturerId)
        return queryHandler.handle(query)
    }
    @GetMapping("/{id}")
    fun getComponentById(@PathVariable id: Long): ResponseEntity<Component> {
        val component = componentRepository.findById(id)
        return if (component != null) {
            ResponseEntity.ok(component)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}