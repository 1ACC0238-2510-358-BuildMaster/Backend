package com.pe.buildmaster_backend.glosary.infrastructure.persistence.jpa.repositories

import org.springframework.stereotype.Component

@Component
class SimpleEventPublisher : GlosaryEventPublisher {
    override fun publicar(evento: Any) {
        println("Evento publicado: $evento")
    }
}