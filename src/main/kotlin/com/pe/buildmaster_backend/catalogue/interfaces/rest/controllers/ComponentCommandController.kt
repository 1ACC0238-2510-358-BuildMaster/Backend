package com.pe.buildmaster_backend.catalogue.interfaces.rest.controllers

import com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers.ComponentCommandHandler
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.interfaces.rest.mappers.ComponentMapper
import com.pe.buildmaster_backend.catalogue.interfaces.rest.resources.CreateComponentResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/catalogue")
class ComponentCommandController(
    private val commandHandler: ComponentCommandHandler
) {
    @PostMapping
    fun createComponent(@RequestBody resource: CreateComponentResource): ResponseEntity<Component> {
        val command = ComponentMapper.toCommand(resource)
        val component = commandHandler.handle(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(component)
    }
    @PutMapping("/{id}")
    fun updateComponent(
        @PathVariable id: Long,
        @RequestBody resource: CreateComponentResource
    ): ResponseEntity<Component> {
        val command = ComponentMapper.toCommand(resource)
        val updated = commandHandler.update(id, command)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun deleteComponent(@PathVariable id: Long): ResponseEntity<Void> {
        return if (commandHandler.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
