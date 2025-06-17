package com.pe.buildmaster_backend.login.interfaces.rest.controllers

import com.pe.buildmaster_backend.login.application.internal.eventhandlers.LoginCommandHandler
import com.pe.buildmaster_backend.login.domain.model.commands.LoginCommand
import com.pe.buildmaster_backend.login.domain.model.valueobjects.Role
import com.pe.buildmaster_backend.login.domain.services.UserApplicationService
import com.pe.buildmaster_backend.login.interfaces.rest.DTO.LoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

data class RegisterRequest(val email: String, val password: String, val name: String, val role: Role) // Added role
data class LoginRequest(val email: String, val password: String, val requiredRole: String) // Added requiredRole
data class UpdateProfileRequest(val biografy: String?, val fotoUrl: String?, val profile: String?, val role: String?) // Added profile and role

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserApplicationService,
    private val loginCommandHandler: LoginCommandHandler // directly inject the handler
) {

    @PostMapping("/register")
    fun register(@RequestBody req: RegisterRequest): ResponseEntity<Void> {
        userService.register(
            email = req.email,
            password = req.password,
            name = req.name,
            role = req.role
        )
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun login(@RequestBody cmd: LoginRequest): ResponseEntity<LoginResponse> {
        val result = loginCommandHandler.handle(
            LoginCommand(
                email = cmd.email,
                password = cmd.password,
                requiredRole = cmd.requiredRole
            )
        )
        return ResponseEntity.ok(LoginResponse(token = result.token, role = result.role))
    }

    @GetMapping("/users")
    fun findByName(@RequestParam name: String): ResponseEntity<Any> {
        val user = userService.findByName(name)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(user)
    }

    @PutMapping("/users/{id}")
    fun updateProfile(
        @PathVariable id: UUID,
        @RequestBody req: UpdateProfileRequest
    ): ResponseEntity<Void> {
        userService.updateProfile(
            userId = id.toString(),
            biografy = req.biografy,
            fotoUrl = req.fotoUrl,
            profile = req.profile,
            role = req.role
        )
        return ResponseEntity.ok().build()
    }
}
