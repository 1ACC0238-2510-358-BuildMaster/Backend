package com.pe.buildmaster_backend.catalogue.interfaces.rest.controllers

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Category
import com.pe.buildmaster_backend.catalogue.domain.model.entities.Manufacturer
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.CategoryRepository
import com.pe.buildmaster_backend.catalogue.infrastructure.persistence.jpa.repositories.ManufacturerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/catalogue")
class CatalogueController(
    private val categoryRepository: CategoryRepository,
    private val manufacturerRepository: ManufacturerRepository
) {

    @GetMapping("/categories")
    fun getCategories(): ResponseEntity<List<Category>> {
        val result = categoryRepository.findAll()
        return ResponseEntity.ok(result)
    }

    @GetMapping("/manufacturers")
    fun getManufacturers(): ResponseEntity<List<Manufacturer>> {
        val result = manufacturerRepository.findAll()
        return ResponseEntity.ok(result)
    }
}