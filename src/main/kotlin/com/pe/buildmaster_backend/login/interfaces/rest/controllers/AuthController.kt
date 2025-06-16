package com.pe.buildmaster_backend.login.interfaces.rest.controllers

import com.pe.buildmaster_backend.login.domain.services.UserApplicationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

data class RegisterRequest(val email: String, val password: String, val name: String)
data class LoginRequest(val email: String, val password: String)
data class UpdateProfileRequest(val biografy: String?, val fotoUrl: String?)

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserApplicationService
) {

    @PostMapping("/register")
    fun register(@RequestBody req: RegisterRequest): ResponseEntity<Void> {
        userService.register(
            email = req.email,
            password = req.password,
            name = req.name
        )
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): ResponseEntity<Map<String, String>> {
        val token = userService.login(
            email = req.email,
            password = req.password
        )
        // return JWT (or whatever token) under "token" key
        return ResponseEntity.ok(mapOf("token" to token))
    }

    @GetMapping("/users")
    fun findByName(@RequestParam name: String): ResponseEntity<Any> {
        val user = userService.findByName(name)
            ?: return ResponseEntity.notFound().build()
        // you can map your domain user to a DTO here
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
            fotoUrl = req.fotoUrl
        )
        return ResponseEntity.ok().build()
    }
}
