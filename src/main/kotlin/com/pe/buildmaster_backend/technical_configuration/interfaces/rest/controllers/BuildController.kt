package com.pe.buildmaster_backend.technical_configuration.interfaces.rest.controllers

import com.pe.buildmaster_backend.technical_configuration.domain.model.entities.Build
import com.pe.buildmaster_backend.technical_configuration.domain.services.BuildService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/builds")
class BuildController(
    private val buildService: BuildService
) {

    data class BuildCreateRequest(
        val componentIds: List<Long>
    )

    @PostMapping
    fun createBuild(@RequestBody request: BuildCreateRequest): ResponseEntity<Build> {
        val savedBuild = buildService.createBuild(request.componentIds)
        return ResponseEntity.ok(savedBuild)
    }

    @GetMapping("/{id}")
    fun getBuildById(@PathVariable id: Long): ResponseEntity<Build> {
        val build = buildService.getBuildById(id)
        return build?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping
    fun getAllBuilds(): ResponseEntity<List<Build>> {
        return ResponseEntity.ok(buildService.getAllBuilds())
    }

    @DeleteMapping("/{id}")
    fun deleteBuild(@PathVariable id: Long): ResponseEntity<Void> {
        buildService.deleteBuild(id)
        return ResponseEntity.noContent().build()
    }
}