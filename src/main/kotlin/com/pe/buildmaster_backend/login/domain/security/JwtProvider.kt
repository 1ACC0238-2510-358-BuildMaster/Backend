package com.pe.buildmaster_backend.login.domain.security

import com.pe.buildmaster_backend.login.domain.model.aggregates.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider {
    private val secretKey = "yourSecretKey" // Replace with a secure key
    private val expirationTime = 86400000L // 1 day in milliseconds

    fun generarToken(userId: String, perfil: String,user: User): String {
        val claims = mapOf("perfil" to perfil)
        return Jwts.builder()
            .setSubject(userId)
            .addClaims(claims)
            .claim("role", user.role.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }
}