package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

interface GlosaryEventPublisher {
    fun publicar(evento: Any)
}