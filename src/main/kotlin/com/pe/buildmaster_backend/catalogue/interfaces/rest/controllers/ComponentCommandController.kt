package com.pe.buildmaster_backend.catalogue.interfaces.rest.controllers

import com.pe.buildmaster_backend.catalogue.application.handlers.ComponentCommandHandler
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.interfaces.rest.mappers.ComponentMapper
import com.pe.buildmaster_backend.catalogue.interfaces.rest.resources.CreateComponentResource
import com.pe.buildmaster_backend.catalogue.interfaces.rest.resources.UpdateComponentResource
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
        println("RECURSO RECIBIDO: $resource")
        val command = ComponentMapper.toCreateCommand(resource)
        val component = commandHandler.handleCreate(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(component)
    }

    @PutMapping("/{id}")
    fun updateComponent(
        @PathVariable id: Long,
        @RequestBody resource: UpdateComponentResource
    ): ResponseEntity<Component> {
        val command = ComponentMapper.toUpdateCommand(resource)
        val updated = commandHandler.handleUpdate(id, command)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun deleteComponent(@PathVariable id: Long): ResponseEntity<Void> {
        commandHandler.handleDelete(id)
        return ResponseEntity.noContent().build()
    }
}
