package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

import com.pe.buildmaster_backend.glosary.domain.model.valueobjects.Guide

interface GuideRepository {
    fun findByCategory(categoria: String): Guide
}