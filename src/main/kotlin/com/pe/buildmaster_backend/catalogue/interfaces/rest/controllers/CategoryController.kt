package com.pe.buildmaster_backend.catalogue.interfaces.rest.controllers

import com.pe.buildmaster_backend.catalogue.application.internal.eventhandlers.CategoryCommandHandler
import com.pe.buildmaster_backend.catalogue.domain.model.commands.CreateCategoryCommand
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.JpaCategoryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryRepository: JpaCategoryRepository,
    private val categoryCommandHandler: CategoryCommandHandler
) {

    @GetMapping
    fun getCategories(): ResponseEntity<List<Map<String, Any>>> {
        val categories = categoryRepository.findAll()
        val result = categories.map { category ->
            mapOf(
                "id" to category.id,
                "name" to category.name
            )
        }
        return ResponseEntity.ok(result)
    }

    @PostMapping
    fun createCategory(@RequestBody command: CreateCategoryCommand): ResponseEntity<Category> {
        val category = categoryCommandHandler.handleCreate(command)
        return ResponseEntity.ok(category)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): ResponseEntity<Void> {
        categoryCommandHandler.handleDelete(id)
        return ResponseEntity.noContent().build()
    }
}
