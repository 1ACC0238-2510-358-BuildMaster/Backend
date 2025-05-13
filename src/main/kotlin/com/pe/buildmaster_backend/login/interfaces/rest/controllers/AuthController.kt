package com.pe.buildmaster_backend.login.interfaces.rest.controllers;

import com.pe.buildmaster_backend.login.domain.model.valueobjects.User
import com.pe.buildmaster_backend.login.domain.services.AuthService;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
class AuthController(
    private val authService:AuthService
) {
    @PostMapping("/register")
    fun register(@RequestBody dto: RegisterDTO): ResponseEntity<User> {
        val user = authService.registrar(dto.email, dto.password, dto.nombre)
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }
    @PostMapping("/login")
    fun login(@RequestBody dto: LoginDTO): ResponseEntity<TokenResponse> {
        val token = authService.login(dto.email, dto.password)
        return ResponseEntity.ok(TokenResponse(token))
    }
}

data class RegisterDTO(val email: String, val password: String, val nombre: String)
data class LoginDTO(val email: String, val password: String)
data class TokenResponse(val token: String)