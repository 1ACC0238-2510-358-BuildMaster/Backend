package com.pe.buildmaster_backend.login.application.internal.eventhandlers

import com.pe.buildmaster_backend.login.domain.events.UserRegisteredEvent
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserRegisteredEventHandler {
    private val log = LoggerFactory.getLogger(javaClass)

    @EventListener
    fun onUserRegistered(event: UserRegisteredEvent) {
        log.info("New user registered: id=${event.userId}, email=${event.email}, name=${event.name}")
        // e.g. send welcome email
    }
}
