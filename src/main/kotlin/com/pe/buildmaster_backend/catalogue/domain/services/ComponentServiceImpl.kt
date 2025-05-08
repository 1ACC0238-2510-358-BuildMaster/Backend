package com.pe.buildmaster_backend.catalogue.domain.services

import com.pe.buildmaster_backend.catalogue.domain.model.entities.Component
import com.pe.buildmaster_backend.catalogue.domain.model.valueobjects.Specifications
import org.springframework.stereotype.Service

@Service
class ComponentServiceImpl : ComponentService {
    override fun isCompatible(existing: Component, newSpecs: Specifications): Boolean {
        return existing.specifications.socket == newSpecs.socket &&
                existing.specifications.memoryType == newSpecs.memoryType
    }
}